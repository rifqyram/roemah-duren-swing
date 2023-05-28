package com.xyz.roemahduren.domain.repository;

import com.xyz.roemahduren.domain.entity.OrderDetail;
import com.xyz.roemahduren.domain.model.response.OrderDetailResponse;

import java.util.List;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, String> {
    List<OrderDetailResponse> getOrderDetailByOrderId(String orderId);
}
