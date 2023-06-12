package com.xyz.roemahduren.application.controller;

import com.xyz.roemahduren.constant.CustomDialog;
import com.xyz.roemahduren.domain.model.response.OrderResponse;
import com.xyz.roemahduren.domain.service.OrderService;
import com.xyz.roemahduren.domain.service.ReportService;
import com.xyz.roemahduren.presentation.component.dialog.DetailTransactionHistoryDialog;
import com.xyz.roemahduren.presentation.component.table.TableActionSelectCellRender;
import com.xyz.roemahduren.presentation.component.table.TableActionSelectedCellEditor;
import com.xyz.roemahduren.presentation.event.TableActionSelectedEvent;
import com.xyz.roemahduren.presentation.screen.TransactionHistoryScreen;
import com.xyz.roemahduren.util.ServiceWorker;
import com.xyz.roemahduren.util.SwingUtil;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.List;

public class TransactionHistoryController {

    private final TransactionHistoryScreen transactionHistoryScreen;
    private final ReportService reportService;
    private final DetailTransactionHistoryDialog detailTransactionHistoryDialog;
    private final CustomDialog dialog;
    private final OrderService orderService;
    private DefaultTableModel model;
    private List<OrderResponse> orderResponses;

    public TransactionHistoryController(TransactionHistoryScreen transactionHistoryScreen, ReportService reportService, DetailTransactionHistoryDialog detailTransactionHistoryDialog, CustomDialog dialog, OrderService orderService) {
        this.transactionHistoryScreen = transactionHistoryScreen;
        this.reportService = reportService;
        this.detailTransactionHistoryDialog = detailTransactionHistoryDialog;
        this.dialog = dialog;
        this.orderService = orderService;
        initController();
        initTable();
    }

    private void initController() {
        transactionHistoryScreen.getPrintBtn().addActionListener(this::generateReport);
    }

    private void generateReport(ActionEvent actionEvent) {
        new ServiceWorker<>(
                () -> {
                    SwingUtil.setLoading(transactionHistoryScreen.getPrintBtn());
                    reportService.generateTransactionReport(MainController.user.getEmail());
                    return true;
                },
                aBoolean -> {},
                throwable -> dialog.getFailedMessageDialog(throwable.getMessage()),
                () -> SwingUtil.clearPrimaryLoading(transactionHistoryScreen.getPrintBtn(), "Print Laporan")
        ).execute();
    }

    private void initTable() {
        String[] HEADERS = {"#", "No. Invoice", "Nama Pelanggan", "Tanggal Pembelian", "Aksi"};
        model = new DefaultTableModel(null, HEADERS);
        transactionHistoryScreen.getTransactionHistoryTable().setModel(model);

        orderResponses = orderService.getAll();

        if (orderResponses.isEmpty()) {
            SwingUtil.setEmptyState(transactionHistoryScreen.getScrollTable());
        } else {
            transactionHistoryScreen.getScrollTable().setViewportView(transactionHistoryScreen.getTransactionHistoryTable());
        }

        int counter = 0;
        for (OrderResponse orderResponse : orderResponses) {
            model.addRow(new Object[] {
                    ++counter,
                    orderResponse.getPurchaseNumber(),
                    orderResponse.getCustomerName(),
                    orderResponse.getTransactionDate(),
            });
        }

        TableActionSelectedEvent selectedEvent = row ->
                detailTransactionHistoryDialog.showDialog(orderResponses.get(row).getOrderDetailResponses());

        transactionHistoryScreen.getTransactionHistoryTable()
                .getColumnModel()
                .getColumn(HEADERS.length - 1)
                .setCellRenderer(new TableActionSelectCellRender("Detail"));
        transactionHistoryScreen.getTransactionHistoryTable()
                .getColumnModel()
                .getColumn(HEADERS.length - 1)
                .setCellEditor(new TableActionSelectedCellEditor(selectedEvent, "Detail"));
    }

    public TransactionHistoryScreen getTransactionHistoryScreen() {
        initTable();
        return transactionHistoryScreen;
    }


}
