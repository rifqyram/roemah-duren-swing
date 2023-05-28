package com.xyz.roemahduren.domain.model.request;

import com.xyz.roemahduren.domain.annotation.validation.Min;
import com.xyz.roemahduren.domain.annotation.validation.NotBlank;
import com.xyz.roemahduren.domain.annotation.validation.NotNull;
import com.xyz.roemahduren.util.RandomGenerator;

import java.math.BigDecimal;

public class ProductRequest {
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String categoryId;
    @NotNull
    @Min(message = "Harga tidak boleh kurang dari 1", value = 1)
    private Long price;
    @NotNull
    @Min(message = "Stock tidak boleh kurang dari 1", value = 1)
    private Integer stock;
    @NotBlank
    private String branchId;
    private Boolean isActive;

    public ProductRequest() {
    }

    public ProductRequest(String name, String categoryId, Long price, Integer stock, String branchId, Boolean isActive) {
        this.id = RandomGenerator.generateUUID();
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
        this.stock = stock;
        this.branchId = branchId;
        this.isActive = isActive;
    }

    public ProductRequest(String id, String name, String categoryId, Long price, Integer stock, String branchId, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
