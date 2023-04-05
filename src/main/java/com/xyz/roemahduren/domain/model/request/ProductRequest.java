package com.xyz.roemahduren.domain.model.request;

public class ProductRequest {
    private String productName;
    private Integer stock;
    private String category;

    public ProductRequest() {
    }

    public ProductRequest(String productName, Integer stock, String category) {
        this.productName = productName;
        this.stock = stock;
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
