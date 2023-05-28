package com.xyz.roemahduren.domain.entity;

import com.xyz.roemahduren.domain.annotation.db.Column;
import com.xyz.roemahduren.domain.annotation.db.Id;
import com.xyz.roemahduren.domain.annotation.db.Table;

import java.time.LocalDateTime;

@Table(name = "t_order")
public class Order {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "po_number")
    private String purchaseNumber;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "trans_date")
    private LocalDateTime transactionDate;

    @Column(name = "order_status")
    private String orderStatus;

    public Order() {
    }

    public Order(String id, String purchaseNumber, String customerId, LocalDateTime transactionDate, String orderStatus) {
        this.id = id;
        this.purchaseNumber = purchaseNumber;
        this.customerId = customerId;
        this.transactionDate = transactionDate;
        this.orderStatus = orderStatus;
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
