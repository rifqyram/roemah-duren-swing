package com.xyz.roemahduren.application.service;

import com.xyz.roemahduren.domain.entity.Customer;
import com.xyz.roemahduren.domain.entity.Order;
import com.xyz.roemahduren.domain.model.request.OrderRequest;
import com.xyz.roemahduren.domain.model.response.OrderDetailResponse;
import com.xyz.roemahduren.domain.model.response.OrderResponse;
import com.xyz.roemahduren.domain.repository.OrderRepository;
import com.xyz.roemahduren.domain.repository.Persistence;
import com.xyz.roemahduren.domain.service.CustomerService;
import com.xyz.roemahduren.domain.service.OrderDetailService;
import com.xyz.roemahduren.domain.service.OrderService;
import com.xyz.roemahduren.exception.NotFoundException;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.xyz.roemahduren.domain.constant.OrderStatus.ORDERED;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final Connection connection;
    private final Persistence persistence;
    private final OrderDetailService orderDetailService;
    private final CustomerService customerService;

    public OrderServiceImpl(OrderRepository orderRepository, Connection connection, Persistence persistence, OrderDetailService orderDetailService, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.connection = connection;
        this.persistence = persistence;
        this.orderDetailService = orderDetailService;
        this.customerService = customerService;
    }

    @Override
    public OrderResponse create(OrderRequest request) {
        return persistence.executeTransaction(connection, () -> {
            Customer customer = customerService.getOrSave(request.getCustomer());
            Order order = new Order(request.getId(), request.getPurchaseNumber(), customer.getId(), LocalDateTime.now());
            orderRepository.save(order);
            List<OrderDetailResponse> orderDetails = orderDetailService.createAll(request.getOrderDetailRequest());
            return getOrderResponse(customer, order, orderDetails);
        });
    }

    @Override
    public OrderResponse getById(String id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new NotFoundException(NotFoundException.ORDER_NOT_FOUND));
        Customer customer = customerService.getById(order.getCustomerId());
        List<OrderDetailResponse> orderDetailResponses = orderDetailService.getAllByOrderId(order.getId());
        return getOrderResponse(customer, order, orderDetailResponses);
    }


    @Override
    public List<OrderResponse> getAll() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order : orders) {
            Customer customer = customerService.getById(order.getCustomerId());
            List<OrderDetailResponse> orderDetailResponses = orderDetailService.getAllByOrderId(order.getId());
            orderResponses.add(getOrderResponse(customer, order, orderDetailResponses));
        }
        return orderResponses;
    }

    private static OrderResponse getOrderResponse(Customer customer, Order order, List<OrderDetailResponse> orderDetails) {
        return new OrderResponse(order.getId(), order.getPurchaseNumber(), customer.getName(), order.getTransactionDate(), orderDetails);
    }
}
