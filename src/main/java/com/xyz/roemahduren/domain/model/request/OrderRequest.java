package com.xyz.roemahduren.domain.model.request;

import com.xyz.roemahduren.util.RandomGenerator;

import java.time.LocalDateTime;
import java.util.List;

public class OrderRequest {

    private String id;

    private String purchaseNumber;

    private CustomerRequest customer;

    private LocalDateTime transactionDate;

    private List<OrderDetailRequest> orderDetailRequest;

    public OrderRequest(CustomerRequest customer, List<OrderDetailRequest> orderDetailRequest) {
        this.id = RandomGenerator.generateUUID();
        this.purchaseNumber = RandomGenerator.generateInvoiceNumber();
        this.customer = customer;
        this.transactionDate = LocalDateTime.now();
        this.orderDetailRequest = orderDetailRequest;
    }

    public OrderRequest(String id, CustomerRequest customer, List<OrderDetailRequest> orderDetailRequest) {
        this.id = id;
        this.purchaseNumber = RandomGenerator.generateInvoiceNumber();
        this.customer = customer;
        this.transactionDate = LocalDateTime.now();
        this.orderDetailRequest = orderDetailRequest;
    }

    public OrderRequest(String id, String purchaseNumber, CustomerRequest customer, LocalDateTime transactionDate, List<OrderDetailRequest> orderDetailRequest) {
        this.id = id;
        this.purchaseNumber = purchaseNumber;
        this.customer = customer;
        this.transactionDate = transactionDate;
        this.orderDetailRequest = orderDetailRequest;
    }

    public OrderRequest() {
        this.id = RandomGenerator.generateUUID();
        this.purchaseNumber = RandomGenerator.generateInvoiceNumber();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(String purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    public CustomerRequest getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerRequest customer) {
        this.customer = customer;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public List<OrderDetailRequest> getOrderDetailRequest() {
        return orderDetailRequest;
    }

    public void setOrderDetailRequest(List<OrderDetailRequest> orderDetailRequest) {
        this.orderDetailRequest = orderDetailRequest;
    }
}
