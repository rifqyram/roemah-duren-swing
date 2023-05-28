package com.xyz.roemahduren.domain.service;

import com.xyz.roemahduren.domain.model.request.OrderRequest;
import com.xyz.roemahduren.domain.model.response.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse create(OrderRequest request);
    OrderResponse getById(String id);
    List<OrderResponse> getAll();

}
