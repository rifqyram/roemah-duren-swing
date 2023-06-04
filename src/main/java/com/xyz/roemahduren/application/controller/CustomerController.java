package com.xyz.roemahduren.application.controller;

import com.xyz.roemahduren.domain.entity.Customer;
import com.xyz.roemahduren.domain.service.CustomerService;
import com.xyz.roemahduren.presentation.screen.CustomerScreen;
import com.xyz.roemahduren.util.SwingUtil;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.List;

public class CustomerController {

    private final CustomerScreen customerScreen;
    private final CustomerService customerService;
    private DefaultTableModel model;
    private List<Customer> customers;

    public CustomerController(CustomerScreen customerScreen, CustomerService customerService) {
        this.customerScreen = customerScreen;
        this.customerService = customerService;
        initTable();
        initController();
    }

    private void initController() {
        customerScreen.getPrintBtn().addActionListener(this::printReport);
    }

    private void printReport(ActionEvent actionEvent) {
    }

    private void initTable() {
        String[] HEADERS = {"#", "Nama Pelanggan", "Nomor Handphone", "Alamat"};
        model = new DefaultTableModel(null, HEADERS);
        customerScreen.getCustomerTable().setModel(model);

        customers = customerService.getAll();

        if (customers.isEmpty()) {
            SwingUtil.setEmptyState(customerScreen.getScrollTable());
        } else {
            customerScreen.getScrollTable().setViewportView(customerScreen.getCustomerTable());
        }

        int counter = 0;
        for (Customer customer : customers) {
            model.addRow(new Object[] {
                    ++counter,
                    customer.getName(),
                    customer.getMobilePhone(),
                    customer.getAddress()
            });
        }
    }

    public CustomerScreen getCustomerScreen() {
        initTable();
        return customerScreen;
    }
}
