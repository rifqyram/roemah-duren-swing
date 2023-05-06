package com.xyz.roemahduren.domain.model.response;

import java.math.BigDecimal;

public class ProductResponse {
    private String productId;

    private String name;

    private BigDecimal price;

    private String category;

    private Integer stock;

    private String branch;

    private String isValid;

    public ProductResponse() {
    }

    public ProductResponse(String productId, String name, BigDecimal price, String category, Integer stock, String branch, Boolean isValid) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.stock = stock;
        this.branch = branch;
        this.isValid = isValid ? "Aktif" : "Tidak Aktif";
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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

    public String getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid ? "Aktif" : "Tidak Aktif";
    }
}
