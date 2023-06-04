package com.xyz.roemahduren.application.controller;

import com.xyz.roemahduren.domain.model.response.OrderResponse;
import com.xyz.roemahduren.domain.service.OrderService;
import com.xyz.roemahduren.presentation.screen.TransactionHistoryScreen;
import com.xyz.roemahduren.util.SwingUtil;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.List;

public class TransactionHistoryController {

    private final TransactionHistoryScreen transactionHistoryScreen;
    private final OrderService orderService;
    private DefaultTableModel model;
    private List<OrderResponse> orderResponses;

    public TransactionHistoryController(TransactionHistoryScreen transactionHistoryScreen, OrderService orderService) {
        this.transactionHistoryScreen = transactionHistoryScreen;
        this.orderService = orderService;
        initController();
        initTable();
    }

    private void initController() {
        transactionHistoryScreen.getPrintBtn().addActionListener(this::generateReport);
    }

    private void generateReport(ActionEvent actionEvent) {

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
                    "+" + orderResponse.getOrderDetailResponses().size() + " Detail"
            });
        }
    }

    public TransactionHistoryScreen getTransactionHistoryScreen() {
        initTable();
        return transactionHistoryScreen;
    }
}
