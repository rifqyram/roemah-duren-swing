package com.xyz.roemahduren.domain.constant;

import com.xyz.roemahduren.exception.NotFoundException;

public enum OrderStatus {
    ORDERED("Ordered"),
    PAID("Ordered"),
    COMPLETE("Ordered");

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static OrderStatus get(String value) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new NotFoundException(NotFoundException.STATUS_ORDER_NOT_FOUND);
    }

}
