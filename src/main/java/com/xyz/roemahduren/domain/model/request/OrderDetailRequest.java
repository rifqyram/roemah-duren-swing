package com.xyz.roemahduren.domain.model.request;

import com.xyz.roemahduren.domain.annotation.validation.Min;
import com.xyz.roemahduren.domain.annotation.validation.NotBlank;
import com.xyz.roemahduren.domain.annotation.validation.NotNull;
import com.xyz.roemahduren.util.RandomGenerator;

import java.time.LocalDateTime;

public class OrderDetailRequest {
    private String id;
    private String orderId;
    @NotBlank
    private String productId;
    private Long productPrice;
    @NotNull
    @Min(value = 0, message = "Kuantitas tidak boleh kurang dari 0")
    private Integer quantity;

    public OrderDetailRequest(String orderId, String productId, Long productPrice, Integer quantity) {
        this.id = RandomGenerator.generateUUID();
        this.orderId = orderId;
        this.productId = productId;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public OrderDetailRequest(String id, String orderId, String productId, Long productPrice, Integer quantity) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
