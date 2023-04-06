package com.xyz.roemahduren.infrastructure.repository;

import com.xyz.roemahduren.domain.entity.Order;
import com.xyz.roemahduren.domain.repository.OrderRepository;

import java.sql.Connection;

public class OrderRepositoryImpl extends CrudRepositoryImpl<Order, String> implements OrderRepository {
    protected OrderRepositoryImpl(Connection connection) {
        super(Order.class, connection);
    }
}
