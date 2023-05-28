package com.xyz.roemahduren.application.service;

import com.xyz.roemahduren.domain.entity.OrderDetail;
import com.xyz.roemahduren.domain.model.request.OrderDetailRequest;
import com.xyz.roemahduren.domain.model.response.OrderDetailResponse;
import com.xyz.roemahduren.domain.model.response.OrderResponse;
import com.xyz.roemahduren.domain.model.response.ProductResponse;
import com.xyz.roemahduren.domain.repository.OrderDetailRepository;
import com.xyz.roemahduren.domain.service.OrderDetailService;
import com.xyz.roemahduren.domain.service.ProductService;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final ProductService productService;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository, ProductService productService) {
        this.orderDetailRepository = orderDetailRepository;
        this.productService = productService;
    }

    @Override
    public OrderDetailResponse create(OrderDetailRequest request) {
        OrderDetail orderDetail = new OrderDetail(request.getId(), request.getOrderId(), request.getProductId(), request.getQuantity());
        orderDetailRepository.save(orderDetail);
        return getOrderDetailResponse(orderDetail);
    }

    @Override
    public List<OrderDetailResponse> createAll(List<OrderDetailRequest> request) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (OrderDetailRequest orderDetailRequest : request) {
            OrderDetail orderDetail = new OrderDetail(orderDetailRequest.getId(), orderDetailRequest.getOrderId(), orderDetailRequest.getProductId(), orderDetailRequest.getQuantity());
            orderDetails.add(orderDetail);
        }

        List<OrderDetailResponse> orderDetailResponses = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetails) {
            orderDetailResponses.add(getOrderDetailResponse(orderDetail));
        }
        return orderDetailResponses;
    }

    @Override
    public List<OrderDetailResponse> getAllByOrderId(String orderId) {
        return orderDetailRepository.getOrderDetailByOrderId(orderId);
    }

    private OrderDetailResponse getOrderDetailResponse(OrderDetail orderDetail) {
        ProductResponse productResponse = productService.getById(orderDetail.getProductId());
        return new OrderDetailResponse(orderDetail.getId(), orderDetail.getOrderId(), productResponse.getName(), orderDetail.getQuantity(), productResponse.getPrice() * orderDetail.getQuantity());
    }
}
