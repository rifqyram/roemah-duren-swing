package com.xyz.roemahduren.domain.model.response;

public class SupplierProductResponse {

    private String id;
    private String supplierName;
    private String productName;
    private Long price;
    private Integer stock;

    public SupplierProductResponse(String id, String supplierName, String productName, Long price, Integer stock) {
        this.id = id;
        this.supplierName = supplierName;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
    }

    public SupplierProductResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
}
