package com.xyz.roemahduren.domain.service;

public interface ReportService {

    void generateInvoice(String orderId, String user);

    void generateCustomerReport(String user);

    void generateTransactionReport(String user);

    void generateSupplierReport(String user);

}
