package com.xyz.roemahduren.application.controller;

import com.xyz.roemahduren.constant.CustomDialog;
import com.xyz.roemahduren.domain.model.response.AuthResponse;
import com.xyz.roemahduren.domain.model.response.OrderDetailResponse;
import com.xyz.roemahduren.domain.model.response.OrderResponse;
import com.xyz.roemahduren.domain.service.OrderService;
import com.xyz.roemahduren.presentation.component.card.Card;
import com.xyz.roemahduren.presentation.component.card.ModelCard;
import com.xyz.roemahduren.presentation.component.menu.MenuItem;
import com.xyz.roemahduren.presentation.screen.DashboardScreen;
import com.xyz.roemahduren.presentation.screen.MainScreen;
import com.xyz.roemahduren.presentation.theme.SystemColor;
import com.xyz.roemahduren.util.Utility;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class MainController {

    public static AuthResponse user;

    private final BranchController branchController;
    private final MainScreen mainScreen;
    private final CategoryController categoryController;
    private final ProductController productController;
    private final OrderController orderController;
    private final SupplierController supplierController;
    private final SettingController settingController;
    private final CustomerController customerController;
    private final TransactionHistoryController transactionHistoryController;
    private final CustomDialog dialog;
    private LoginController loginController;

    private final OrderService orderService;
    private List<OrderResponse> orderResponses;

    public MainController(MainScreen mainScreen, BranchController branchController, CategoryController categoryController, ProductController productController, OrderController orderController, SupplierController supplierController, SettingController settingController, CustomerController customerController, TransactionHistoryController transactionHistoryController, CustomDialog dialog, OrderService orderService) {
        this.mainScreen = mainScreen;
        this.branchController = branchController;
        this.categoryController = categoryController;
        this.productController = productController;
        this.orderController = orderController;
        this.supplierController = supplierController;
        this.settingController = settingController;
        this.customerController = customerController;
        this.transactionHistoryController = transactionHistoryController;
        this.dialog = dialog;
        this.orderService = orderService;
        initController();
        initTable();
        initCard();
    }

    private void initController() {
        runEvent();
    }

    private void runEvent() {
        List<MenuItem> menuItems = mainScreen.getSideMenuPanel().getMenuItems();

        for (int i = 0; i < menuItems.size(); i++) {
            int index = i;

            menuItems.get(i).addActionListener(e -> {
                setSelected(menuItems, index);
                selectedMenu(menuItems, index);
            });
        }
    }

    private void selectedMenu(List<MenuItem> menuItems, int index) {
        for (int j = 0; j < menuItems.size(); j++) {
            if (j == index) {
                menuItems.get(j).setForeground(SystemColor.BUTTON_TEXT_COLOR);
                menuItems.get(j).setColorOver(SystemColor.PRIMARY_COLOR_ACTIVE_BUTTON);
                menuItems.get(j).setBorderColor(SystemColor.PRIMARY_COLOR_ACTIVE_BUTTON);
                selectMenuIndex(index);
            } else {
                menuItems.get(j).setForeground(SystemColor.BUTTON_TEXT_COLOR);
                menuItems.get(j).setColor(SystemColor.PRIMARY_COLOR_BUTTON);
                menuItems.get(j).setBorderColor(SystemColor.PRIMARY_COLOR_BUTTON);
            }
        }
    }

    private void selectMenuIndex(int index) {
        try {
            switch (index) {
                case 0:
                    if (!Objects.isNull(user)) {
                        String[] email = user.getEmail().split("@");
                        String username = Utility.toTitleCase(email[0]);
                        mainScreen.getDashboardScreen().getWelcomeLabel().setText("Hi, " + username + "!");
                    } else {
                        dialog.getFailedMessageDialog("Session anda telah habis silakan login ulang");
                        mainScreen.setVisible(false);
                        mainScreen.dispose();
                    }
                    setViewport(mainScreen.getDashboardScreen());
                    break;
                case 1:
                    setViewport(orderController.getOrderScreen());
                    break;
                case 2:
                    setViewport(branchController.getBranchScreen());
                    break;
                case 3:
                    setViewport(productController.getProductScreen());
                    break;
                case 4:
                    setViewport(categoryController.getCategoryScreen());
                    break;
                case 5:
                    setViewport(supplierController.getSupplierScreen());
                    break;
                case 6:
                    setViewport(customerController.getCustomerScreen());
                    break;
                case 7:
                    setViewport(transactionHistoryController.getTransactionHistoryScreen());
                    break;
                case 8:
                    setViewport(settingController.getSettingScreen());
                    break;
                case 9:
                    int confirmDeleteDialog = dialog.getConfirmInfoDialog(CustomDialog.LOGOUT_CONFIRMATION);
                    if (confirmDeleteDialog != 1) return;
                    loginController.getLoginScreen().setVisible(true);
                    mainScreen.setVisible(false);
                    mainScreen.dispose();
                    break;
            }
        } catch (Exception e) {
            dialog.getFailedMessageDialog(e.getMessage());
        }
    }

    private void setViewport(Component component) {
        mainScreen.getContentMenuScroll().setViewportView(component);
    }

    public MainScreen getMainScreen() {
        return mainScreen;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    private void setSelected(List<MenuItem> menuItems, int index) {
        menuItems.get(index).setSelected(true);
        for (int j = 0; j < menuItems.size(); j++) {
            if (j != index) {
                menuItems.get(j).setSelected(false);
            }
        }
    }

    private void initTable() {
        String[] HEADERS = {"#", "Nama Pelanggan", "Nama Produk", "Kuantitas", "Total Harga"};
        DefaultTableModel model = new DefaultTableModel(null, HEADERS);
        mainScreen.getDashboardScreen().getTable1().setModel(model);

        orderResponses = orderService.getAll();
        int counter = 1;
        for (OrderResponse orderResponse : orderResponses) {
            for (OrderDetailResponse orderDetailResponse : orderResponse.getOrderDetailResponses()) {
                model.addRow(new Object[]{
                        counter++,
                        orderResponse.getCustomerName(),
                        orderDetailResponse.getProductName(),
                        orderDetailResponse.getQuantity(),
                        orderDetailResponse.getTotalPrice()
                });
            }
        }
    }

    private void initCard() {

        long totalRevenue = 0;
        for (OrderResponse orderResponse : orderResponses) {
            LongSummaryStatistics collect = orderResponse.getOrderDetailResponses().stream().collect(Collectors.summarizingLong(OrderDetailResponse::getTotalPrice));
            totalRevenue += collect.getSum();
        }

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        Currency rupiah = Currency.getInstance("IDR");
        currencyFormatter.setCurrency(rupiah);
        String formattedAmount = currencyFormatter.format(totalRevenue);

        Card card1 = mainScreen.getDashboardScreen().getCard1();
        card1.setData(new ModelCard(
                new ImageIcon(getClass().getResource("/images/Receipt long.png")),
                "Total Pendapatan",
                formattedAmount
        ));

        Card card2 = mainScreen.getDashboardScreen().getCard2();
        card2.setData(new ModelCard(
                new ImageIcon(getClass().getResource("/images/Box.png")),
                "Total Transaksi",
                String.valueOf(orderResponses.size())
        ));
    }

}
