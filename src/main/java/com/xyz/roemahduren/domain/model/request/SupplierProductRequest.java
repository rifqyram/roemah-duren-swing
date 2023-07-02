package com.xyz.roemahduren.domain.model.request;

import com.xyz.roemahduren.domain.annotation.validation.Min;
import com.xyz.roemahduren.domain.annotation.validation.NotBlank;
import com.xyz.roemahduren.domain.annotation.validation.NotNull;
import com.xyz.roemahduren.util.RandomGenerator;

public class SupplierProductRequest {
    private String id;
    @NotBlank
    private String supplierId;
    @NotBlank
    private String productName;
    @NotNull
    @Min(message = "Harga tidak boleh kurang dari 1", value = 1)
    private Long price;
    @NotNull
    @Min(message = "Stock tidak boleh kurang dari 1", value = 1)
    private Integer stock;

    private Integer initStock;

    public SupplierProductRequest(String supplierId, String productName, Long price, Integer stock, Integer initStock) {
        this.id = RandomGenerator.generateUUID();
        this.supplierId = supplierId;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.initStock = initStock;
    }

    public SupplierProductRequest(String supplierId, String productName, Long price, Integer stock) {
        this.id = RandomGenerator.generateUUID();
        this.supplierId = supplierId;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
    }

    public SupplierProductRequest(String id, String supplierId, String productName, Long price, Integer stock) {
        this.id = id;
        this.supplierId = supplierId;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
    }

    public SupplierProductRequest(String id, String supplierId, String productName, Long price, Integer stock, Integer initStock) {
        this.id = id;
        this.supplierId = supplierId;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.initStock = initStock;
    }

    public SupplierProductRequest() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
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

    public Integer getInitStock() {
        return initStock;
    }

    public void setInitStock(Integer initStock) {
        this.initStock = initStock;
    }
}
