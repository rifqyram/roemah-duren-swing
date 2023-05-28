package com.xyz.roemahduren.domain.model.response;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponse {
    private String id;
    private String purchaseNumber;
    private String customerName;
    private LocalDateTime transactionDate;
    private List<OrderDetailResponse> orderDetailResponses;

    public OrderResponse(String id, String purchaseNumber, String customerName, LocalDateTime transactionDate, List<OrderDetailResponse> orderDetailResponses) {
        this.id = id;
        this.purchaseNumber = purchaseNumber;
        this.customerName = customerName;
        this.transactionDate = transactionDate;
        this.orderDetailResponses = orderDetailResponses;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public List<OrderDetailResponse> getOrderDetailResponses() {
        return orderDetailResponses;
    }

    public void setOrderDetailResponses(List<OrderDetailResponse> orderDetailResponses) {
        this.orderDetailResponses = orderDetailResponses;
    }
}
