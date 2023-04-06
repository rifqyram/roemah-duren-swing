package com.xyz.roemahduren.domain.entity;

import com.xyz.roemahduren.domain.annotation.db.Column;
import com.xyz.roemahduren.domain.annotation.db.Id;
import com.xyz.roemahduren.domain.annotation.db.Table;

@Table(name = "t_order")
public class Order {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "po_number")
    private String purchaseNumber;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "transaction_date")
    private Long transactionDate;

    public Order(String customerId) {
        this.customerId = customerId;
    }

    public Order(String id, String purchaseNumber, String customerId, String transactionType, Long transactionDate) {
        this.id = id;
        this.purchaseNumber = purchaseNumber;
        this.customerId = customerId;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
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

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Long getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Long transactionDate) {
        this.transactionDate = transactionDate;
    }
}
