package com.xyz.roemahduren.domain.model.request;

import com.xyz.roemahduren.domain.annotation.validation.NotBlank;
import com.xyz.roemahduren.util.RandomGenerator;

public class ProductRequest {
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String categoryId;
    private ProductPriceRequest productPriceRequest;
    private Boolean isActive;

    public ProductRequest() {
    }

    public ProductRequest(String name, String categoryId, ProductPriceRequest productPriceRequest) {
        this.id = RandomGenerator.generateUUID();
        this.name = name;
        this.categoryId = categoryId;
        this.productPriceRequest = productPriceRequest;
        this.isActive = true;
    }

    public ProductRequest(String id, String name, String categoryId, ProductPriceRequest productPriceRequest, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.productPriceRequest = productPriceRequest;
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

    public ProductPriceRequest getProductPriceRequest() {
        return productPriceRequest;
    }

    public void setProductPriceRequest(ProductPriceRequest productPriceRequest) {
        this.productPriceRequest = productPriceRequest;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
