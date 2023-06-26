package com.xyz.roemahduren.application.controller;

import com.xyz.roemahduren.constant.ConstantMessage;
import com.xyz.roemahduren.constant.CustomDialog;
import com.xyz.roemahduren.domain.entity.InvoiceNumber;
import com.xyz.roemahduren.domain.model.request.CustomerRequest;
import com.xyz.roemahduren.domain.model.request.OrderDetailRequest;
import com.xyz.roemahduren.domain.model.request.OrderRequest;
import com.xyz.roemahduren.domain.model.response.ErrorValidationModel;
import com.xyz.roemahduren.domain.model.response.OrderDetailResponse;
import com.xyz.roemahduren.domain.model.response.ProductResponse;
import com.xyz.roemahduren.domain.service.InvoiceNumberService;
import com.xyz.roemahduren.domain.service.OrderService;
import com.xyz.roemahduren.domain.service.ProductService;
import com.xyz.roemahduren.exception.ValidationException;
import com.xyz.roemahduren.presentation.component.input.RoundedTextFieldPanel;
import com.xyz.roemahduren.presentation.component.table.TableActionDeleteCellEditor;
import com.xyz.roemahduren.presentation.component.table.TableActionDeleteCellRender;
import com.xyz.roemahduren.presentation.component.table.TableActionSelectCellRender;
import com.xyz.roemahduren.presentation.component.table.TableActionSelectedCellEditor;
import com.xyz.roemahduren.presentation.event.TableActionSelectedEvent;
import com.xyz.roemahduren.presentation.screen.OrderScreen;
import com.xyz.roemahduren.util.RandomGenerator;
import com.xyz.roemahduren.util.ServiceWorker;
import com.xyz.roemahduren.util.SwingUtil;
import com.xyz.roemahduren.util.ValidationUtil;

import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class OrderController {

    private final OrderScreen orderScreen;
    private final OrderService orderService;
    private final ProductService productService;
    private final CustomDialog dialog;
    private final InvoiceNumberService invoiceNumberService;

    private DefaultTableModel productTableModel;
    private DefaultTableModel detailOrderTableModel;
    private ProductResponse productResponse;
    private OrderDetailResponse orderDetailResponse;
    private List<ProductResponse> productResponses;
    private List<OrderDetailResponse> orderDetailResponses;

    public OrderController(OrderScreen orderScreen, OrderService orderService, ProductService productService, CustomDialog dialog, InvoiceNumberService invoiceNumberService) {
        this.orderScreen = orderScreen;
        this.orderService = orderService;
        this.productService = productService;
        this.dialog = dialog;
        this.invoiceNumberService = invoiceNumberService;
        this.orderDetailResponses = new ArrayList<>();

        orderScreen.getProductNameTf().getTextField().setEnabled(false);
        orderScreen.getProductCategoryTf().getTextField().setEnabled(false);
        orderScreen.getDetailDataTransactionPanel().setVisible(false);
        orderScreen.getQuantitySpinner().getRoundedSpinner().setEnabled(false);

        initTableProduct();
        initTableOrderDetail();
        initController();
    }

    public OrderScreen getOrderScreen() {
        initTableProduct();
        initTableOrderDetail();
        return orderScreen;
    }

    private void initController() {
        orderScreen.getAddProductBtn().addActionListener(this::addToCart);
        orderScreen.getCheckboxAsGuest().getCheckbox().addMouseListener(setAnonymousOrder());
        orderScreen.getClearCartBtn().addActionListener(this::clearCart);
        orderScreen.getSearchBtn().addActionListener(this::searchProduct);
        orderScreen.getQuantitySpinner().getRoundedSpinner().addChangeListener(handleChangeQuantity());
        orderScreen.getCheckoutBtn().addActionListener(this::checkout);
    }

    private void checkout(ActionEvent actionEvent) {
        if (dialog.getConfirmInfoDialog(CustomDialog.CHECKOUT_CONFIRMATION) != 1) return;
        new ServiceWorker<>(
                () -> {
                    if (orderDetailResponses.isEmpty()) throw new RuntimeException("Keranjang tidak boleh kosong");
                    CustomerRequest customerRequest;
                    if (orderScreen.getCheckboxAsGuest().getCheckbox().isSelected()) {
                        customerRequest = new CustomerRequest(ConstantMessage.CUSTOMER, null, null);
                    } else {
                        customerRequest = new CustomerRequest(orderScreen.getCustomerNameTf().getValue(), orderScreen.getCustomerPhoneNumberTf().getValue(), null);
                    }

                    OrderRequest request = new OrderRequest();
                    Date date = new Date(System.currentTimeMillis());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String format = simpleDateFormat.format(date);

                    InvoiceNumber currentInvoice = invoiceNumberService.getByDate(Date.valueOf(format));
                    String invoiceNumber = invoiceNumberService.generateInvoiceNumber(currentInvoice);
                    request.setPurchaseNumber(invoiceNumber);
                    List<OrderDetailRequest> orderDetailRequests = orderDetailResponses.stream()
                            .map(odr -> new OrderDetailRequest(request.getId(), odr.getProductId(), odr.getProductPrice(), odr.getQuantity()))
                            .collect(Collectors.toList());
                    request.setCustomer(customerRequest);
                    request.setOrderDetailRequest(orderDetailRequests);
                    return orderService.create(request);
                },
                result -> {
                    orderDetailResponses = new ArrayList<>();
                    dialog.getSuccessMessageDialog(ConstantMessage.SUCCESS_CHECKOUT);
                    initTableOrderDetail();
                    initTableProduct();
                },
                throwable -> {
                    dialog.getFailedMessageDialog(throwable.getMessage());
                },
                () -> {
                }
        ).execute();
    }

    private ChangeListener handleChangeQuantity() {
        return changeEvent -> {
            int value = orderScreen.getQuantitySpinner().getValue();
            if (value >= productResponse.getStock()) {
                orderScreen.getQuantitySpinner().getRoundedSpinner().setValue(productResponse.getStock());
            }
        };
    }

    private void searchProduct(ActionEvent actionEvent) {
        RoundedTextFieldPanel searchTextField = orderScreen.getSearchProductTextField();
        if (searchTextField.getValue().isEmpty() || searchTextField.getValue().equals("")) {
            initTableProduct();
            return;
        }

        productResponses = productService.getAllByName(searchTextField.getValue());

        if (productResponses.isEmpty()) {
            SwingUtil.setEmptyState(orderScreen.getScrollProductTable());
        } else {
            orderScreen.getScrollProductTable().setViewportView(orderScreen.getProductTable());
        }

        productTableModel.setRowCount(0);
        int counter = 0;
        for (ProductResponse product : productResponses) {
            productTableModel.addRow(new Object[]{++counter, product.getName(), product.getPrice(), product.getStock()});
        }
    }

    private void clearCart(ActionEvent actionEvent) {
        int confirmInfoDialog = dialog.getConfirmInfoDialog("Apakah anda yakin ingin menghapus semua isi keranjang?");
        if (confirmInfoDialog != 1) return;
        orderDetailResponses.clear();
        initTableOrderDetail();
    }

    private MouseAdapter setAnonymousOrder() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean value = orderScreen.getCheckboxAsGuest().getValue();
                if (!value) {
                    orderScreen.getCustomerNameTf().getTextField().setEnabled(true);
                    orderScreen.getCustomerPhoneNumberTf().getTextField().setEnabled(true);
                    orderScreen.getCustomerNameTf().setValue("");
                    return;
                }
                orderScreen.getCustomerNameTf().setValue("Pelanggan");
                orderScreen.getCustomerNameTf().getTextField().setEnabled(false);
                orderScreen.getCustomerPhoneNumberTf().getTextField().setEnabled(false);
            }
        };
    }

    private void clearProductListPanel() {
        orderScreen.getProductNameTf().setValue("");
        orderScreen.getProductCategoryTf().setValue("");
        orderScreen.getQuantitySpinner().getRoundedSpinner().setEnabled(false);
        orderScreen.getQuantitySpinner().setValue(1);
        orderDetailResponse = null;
        productResponse = null;
    }

    private void clearError() {
        orderScreen.getQuantitySpinner().clearErrorMessage();
    }

    private void addToCart(ActionEvent actionEvent) {
        try {
            clearError();

            HashSet<ErrorValidationModel> errors = new HashSet<>();
            if (orderScreen.getQuantitySpinner().getValue() > productResponse.getStock()) {
                errors.add(new ErrorValidationModel("quantity", new HashSet<>(Collections.singletonList("Kuantitas tidak bisa melebihi stok produk"))));
                throw new ValidationException(errors);
            }

            Optional<OrderDetailResponse> detailResponseOptional = orderDetailResponses
                    .stream()
                    .filter(orderDetailResponse -> orderDetailResponse.getProductName().equals(productResponse.getName()))
                    .collect(Collectors.toList()).stream().findFirst();

            if (detailResponseOptional.isPresent()) {
                orderDetailResponse = detailResponseOptional.get();

                int totalQuantity = orderDetailResponse.getQuantity() + orderScreen.getQuantitySpinner().getValue();
                if (totalQuantity > productResponse.getStock()) {
                    errors.add(new ErrorValidationModel("quantity", new HashSet<>(Collections.singletonList("Stok Habis/Tidak Cukup"))));
                    throw new ValidationException(errors);
                }

                orderDetailResponse.setQuantity(totalQuantity);
                orderDetailResponse.setTotalPrice(orderDetailResponse.getQuantity() * productResponse.getPrice());
                initTableOrderDetail();
                clearProductListPanel();
                dialog.getSuccessUpdateMessageDialog(ConstantMessage.ORDER_DETAIL);
                return;
            }

            if (productResponse != null) {
                orderDetailResponse = new OrderDetailResponse(
                        RandomGenerator.generateUUID(),
                        null,
                        productResponse.getId(),
                        productResponse.getName(),
                        productResponse.getPrice(),
                        orderScreen.getQuantitySpinner().getValue(),
                        orderScreen.getQuantitySpinner().getValue() * productResponse.getPrice()
                );
                orderDetailResponses.add(orderDetailResponse);
                initTableOrderDetail();
                clearProductListPanel();
                dialog.getSuccessMessageDialog(ConstantMessage.SUCCESS_ADD_ORDER_DETAIL);
            }
        } catch (RuntimeException e) {
            if (e instanceof ValidationException) {
                setErrorValidation((ValidationException) e);
                return;
            }
            dialog.getFailedMessageDialog(e.getMessage());
        }
    }

    private void setErrorValidation(ValidationException e) {
        Set<ErrorValidationModel> validationModels = e.getValidationModels();
        for (ErrorValidationModel validationModel : validationModels) {
            if (validationModel.getPath().equals("quantity")) {
                orderScreen.getQuantitySpinner().setErrorMessage(ValidationUtil.getMessage(validationModel.getMessages()));
            }
        }
    }

    private void initTableProduct() {
        final String[] HEADERS = {"#", "Nama Produk", "Harga", "Stok", "Aksi"};
        productTableModel = new DefaultTableModel(null, HEADERS);
        productResponses = productService.getAll(true);

        if (productResponses.isEmpty()) {
            SwingUtil.setEmptyState(orderScreen.getScrollProductTable());
        } else {
            orderScreen.getScrollProductTable().setViewportView(orderScreen.getProductTable());
        }

        int counter = 0;
        for (ProductResponse productResponse : productResponses) {
            productTableModel.addRow(new Object[]{++counter, productResponse.getName(), productResponse.getPrice(), productResponse.getStock()});
        }

        orderScreen.getProductTable().setModel(productTableModel);
        TableActionSelectedEvent tableActionEvent = getTableActionSelectEvent();
        orderScreen.getProductTable().getColumnModel().getColumn(HEADERS.length - 1).setCellRenderer(new TableActionSelectCellRender());
        orderScreen.getProductTable().getColumnModel().getColumn(HEADERS.length - 1).setCellEditor(new TableActionSelectedCellEditor(tableActionEvent));
    }

    private void initTableOrderDetail() {
        {
            orderScreen.getDetailDataTransactionPanel().setVisible(true);
            orderScreen.getClearCartBtn().setVisible(true);

            final String[] HEADERS = {"#", "Nama Produk", "Kuantitas", "Total Harga", "Aksi"};
            detailOrderTableModel = new DefaultTableModel(null, HEADERS);

            if (orderDetailResponses.isEmpty()) {
                SwingUtil.setEmptyState(orderScreen.getScrollTransactionDetail());
                orderScreen.getDetailDataTransactionPanel().setVisible(false);
                orderScreen.getClearCartBtn().setVisible(false);
            } else {
                orderScreen.getScrollTransactionDetail().setViewportView(orderScreen.getDetailTransactionTable());
            }

            int counter = 0;
            for (OrderDetailResponse orderDetailResponse : orderDetailResponses) {
                detailOrderTableModel.addRow(new Object[]{++counter, orderDetailResponse.getProductName(), orderDetailResponse.getQuantity(), orderDetailResponse.getTotalPrice()});
            }

            if (!orderDetailResponses.isEmpty()) {
                LongSummaryStatistics collect = orderDetailResponses.stream().collect(Collectors.summarizingLong(OrderDetailResponse::getTotalPrice));
                String stringPrice = "Rp " + collect.getSum();
                orderScreen.getTotalPriceText().setText(stringPrice);
            }

            orderScreen.getDetailTransactionTable().setModel(detailOrderTableModel);
            TableActionSelectedEvent tableActionEvent = getTableActionDelete();
            orderScreen.getDetailTransactionTable().getColumnModel().getColumn(HEADERS.length - 1).setCellRenderer(new TableActionDeleteCellRender());
            orderScreen.getDetailTransactionTable().getColumnModel().getColumn(HEADERS.length - 1).setCellEditor(new TableActionDeleteCellEditor(tableActionEvent));
        }
    }

    private TableActionSelectedEvent getTableActionDelete() {
        return row -> {
            OrderDetailResponse orderDetailResponse = orderDetailResponses.get(row);
            orderDetailResponses.remove(orderDetailResponse);
            initTableOrderDetail();
            dialog.getSuccessMessageDialog(ConstantMessage.SUCCESS_REMOVE_ORDER_DETAIL);
        };
    }

    private TableActionSelectedEvent getTableActionSelectEvent() {
        return row -> {
            productResponse = productResponses.get(row);
            orderScreen.getProductNameTf().getTextField().setText(productResponse.getName());
            orderScreen.getProductCategoryTf().setValue(productResponse.getCategory());
            orderScreen.getQuantitySpinner().getRoundedSpinner().setEnabled(true);
        };
    }
}

