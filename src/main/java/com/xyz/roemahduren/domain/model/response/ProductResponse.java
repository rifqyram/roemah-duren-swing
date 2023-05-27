package com.xyz.roemahduren.domain.model.response;

import java.math.BigDecimal;

public class ProductResponse {
    private String id;

    private String name;

    private Long price;

    private String category;

    private Integer stock;

    private String branch;

    private Boolean isValid;

    public ProductResponse() {
    }

    public ProductResponse(String productId, String name, Long price, String category, Integer stock, String branch, Boolean isValid) {
        this.id = productId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.stock = stock;
        this.branch = branch;
        this.isValid = isValid;
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

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getValidString() {
        return isValid ? "Aktif" : "Tidak Aktif";
    }

    public Boolean getValid () {
        return isValid;
    }

    public void setValid(Boolean valid) {
        this.isValid = valid;
    }
}
