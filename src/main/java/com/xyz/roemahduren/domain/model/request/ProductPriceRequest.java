package com.xyz.roemahduren.domain.model.request;

import com.xyz.roemahduren.domain.annotation.validation.Min;
import com.xyz.roemahduren.domain.annotation.validation.NotBlank;
import com.xyz.roemahduren.domain.annotation.validation.NotNull;
import com.xyz.roemahduren.util.RandomGenerator;

import java.math.BigDecimal;

public class ProductPriceRequest {
    private String id;
    @NotBlank
    @Min(message = "Harga tidak boleh kurang dari 0", value = 0)
    private BigDecimal price;
    @NotNull
    @Min(message = "Stock tidak boleh kurang dari 0", value = 0)
    private Integer stock;
    private String productId;
    @NotBlank
    private String branchId;
    private Boolean isValid;

    public ProductPriceRequest() {
    }

    public ProductPriceRequest(BigDecimal price, Integer stock, String productId, String branchId) {
        this.id = RandomGenerator.generateUUID();
        this.price = price;
        this.stock = stock;
        this.productId = productId;
        this.branchId = branchId;
    }

    public ProductPriceRequest(String id, BigDecimal price, Integer stock, String productId, String branchId, Boolean isValid) {
        this.id = id;
        this.price = price;
        this.stock = stock;
        this.productId = productId;
        this.branchId = branchId;
        this.isValid = isValid;
    }

    public ProductPriceRequest(String id, BigDecimal price, Integer stock, String productId, String branchId) {
        this.id = id;
        this.price = price;
        this.stock = stock;
        this.productId = productId;
        this.branchId = branchId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public Boolean getActive() {
        return isValid;
    }

    public void setActive(Boolean valid) {
        isValid = valid;
    }
}
