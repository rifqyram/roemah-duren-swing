package com.xyz.roemahduren.domain.service;

import com.xyz.roemahduren.domain.model.request.OrderDetailRequest;
import com.xyz.roemahduren.domain.model.response.OrderDetailResponse;

import java.util.List;

public interface OrderDetailService {

    OrderDetailResponse create(OrderDetailRequest request);
    List<OrderDetailResponse> createAll(List<OrderDetailRequest> request);
    List<OrderDetailResponse> getAllByOrderId(String orderId);

}
