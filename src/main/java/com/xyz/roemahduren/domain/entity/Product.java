package com.xyz.roemahduren.domain.entity;

import com.xyz.roemahduren.domain.annotation.db.Column;
import com.xyz.roemahduren.domain.annotation.db.Id;
import com.xyz.roemahduren.domain.annotation.db.Table;

import java.math.BigDecimal;

@Table(name = "m_product")
public class Product {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "branch_id")
    private String branchId;

    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "is_active")
    private Boolean isActive;

    public Product() {
    }

    public Product(String id, String name, Long price, Integer stock, String branchId, String categoryId, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.branchId = branchId;
        this.categoryId = categoryId;
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean valid) {
        isActive = valid;
    }
}
