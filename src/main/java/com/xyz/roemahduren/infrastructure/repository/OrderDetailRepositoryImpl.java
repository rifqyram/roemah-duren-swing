package com.xyz.roemahduren.infrastructure.repository;

import com.xyz.roemahduren.domain.entity.OrderDetail;
import com.xyz.roemahduren.domain.repository.OrderDetailRepository;

import java.sql.Connection;

public class OrderDetailRepositoryImpl extends CrudRepositoryImpl<OrderDetail, String> implements OrderDetailRepository {
    protected OrderDetailRepositoryImpl(Connection connection) {
        super(OrderDetail.class, connection);
    }
}
