package com.xyz.roemahduren.domain.entity;

import com.xyz.roemahduren.domain.annotation.db.Column;
import com.xyz.roemahduren.domain.annotation.db.Id;
import com.xyz.roemahduren.domain.annotation.db.Table;

import java.math.BigDecimal;

@Table(name = "m_product_price")
public class ProductPrice {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_price")
    private BigDecimal productPrice;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "branch_id")
    private String branchId;

    @Column(name = "is_active")
    private Boolean isActive = true;

    public ProductPrice() {
    }

    public ProductPrice(String id, String productId, BigDecimal productPrice, Integer stock, String branchId, Boolean isActive) {
        this.id = id;
        this.productId = productId;
        this.productPrice = productPrice;
        this.stock = stock;
        this.branchId = branchId;
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
