package com.xyz.roemahduren.domain.entity;

import com.xyz.roemahduren.domain.annotation.db.Column;
import com.xyz.roemahduren.domain.annotation.db.Id;
import com.xyz.roemahduren.domain.annotation.db.Table;

@Table(name = "t_order_detail")
public class OrderDetail {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "product_price_id")
    private String productPriceId;

    @Column(name = "quantity")
    private Integer quantity;

    public OrderDetail() {
    }

    public OrderDetail(String id, String orderId, String productPriceId, Integer quantity) {
        this.id = id;
        this.orderId = orderId;
        this.productPriceId = productPriceId;
        this.quantity = quantity;
    }


}
