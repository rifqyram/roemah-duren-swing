package com.xyz.roemahduren.infrastructure.repository;

import com.xyz.roemahduren.domain.entity.OrderDetail;
import com.xyz.roemahduren.domain.model.response.OrderDetailResponse;
import com.xyz.roemahduren.domain.repository.OrderDetailRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailRepositoryImpl extends CrudRepositoryImpl<OrderDetail, String> implements OrderDetailRepository {
    private final Connection connection;

    protected OrderDetailRepositoryImpl(Connection connection) {
        super(OrderDetail.class, connection);
        this.connection = connection;
    }

    @Override
    public List<OrderDetailResponse> getOrderDetailByOrderId(String orderId) {
        String sql = "SELECT tod.id                       as id,\n" +
                "       tod.order_id                 as order_id,\n" +
                "       mp.id                        as product_id,\n" +
                "       msp.product_name             as product_name,\n" +
                "       tod.quantity                 as quantity,\n" +
                "       SUM(mp.price * tod.quantity) as total_price\n" +
                "FROM t_order_detail as tod\n" +
                "         JOIN m_product mp on mp.id = tod.product_id\n" +
                "         JOIN m_supplier_product msp on msp.id = mp.supplier_product_id\n" +
                "WHERE tod.order_id = ? \n" +
                "GROUP BY id";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, orderId);
            ResultSet resultSet = statement.executeQuery();

            List<OrderDetailResponse> orderDetailResponses = new ArrayList<>();
            while (resultSet.next()) {
                orderDetailResponses.add(new OrderDetailResponse(
                        resultSet.getString("id"),
                        resultSet.getString("order_id"),
                        resultSet.getString("product_id"),
                        resultSet.getString("product_name"),
                        resultSet.getInt("quantity"),
                        resultSet.getLong("total_price")
                ));
            }
            return orderDetailResponses;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
