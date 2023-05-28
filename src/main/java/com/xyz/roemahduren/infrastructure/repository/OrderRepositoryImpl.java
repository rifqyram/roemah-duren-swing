package com.xyz.roemahduren.infrastructure.repository;

import com.xyz.roemahduren.domain.entity.Order;
import com.xyz.roemahduren.domain.model.response.OrderResponse;
import com.xyz.roemahduren.domain.repository.OrderRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryImpl extends CrudRepositoryImpl<Order, String> implements OrderRepository {

    protected OrderRepositoryImpl(Connection connection) {
        super(Order.class, connection);
    }

}
