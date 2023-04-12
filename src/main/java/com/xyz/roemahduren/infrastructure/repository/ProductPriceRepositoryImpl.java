package com.xyz.roemahduren.infrastructure.repository;

import com.xyz.roemahduren.domain.entity.ProductPrice;
import com.xyz.roemahduren.domain.repository.ProductPriceRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductPriceRepositoryImpl extends CrudRepositoryImpl<ProductPrice, String> implements ProductPriceRepository {
    private final Connection connection;

    public ProductPriceRepositoryImpl(Connection connection) {
        super(ProductPrice.class, connection);
        this.connection = connection;
    }

    @Override
    public List<ProductPrice> findAllByProductId(String productId) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM m_product_price WHERE product_id = ?")) {
            statement.setString(1, productId);

            ResultSet resultSet = statement.executeQuery();

            return getProductPrices(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProductPrice> findAllByProductId(String productId, boolean isValid) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM m_product_price WHERE product_id = ? AND is_active = ?")) {
            statement.setString(1, productId);
            statement.setBoolean(2, isValid);

            ResultSet resultSet = statement.executeQuery();

            return getProductPrices(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<ProductPrice> getProductPrices(ResultSet resultSet) throws SQLException {
        List<ProductPrice> productPrices = new ArrayList<>();
        while (resultSet.next()) {
            productPrices.add(new ProductPrice(
                    resultSet.getString("id"),
                    resultSet.getString("product_id"),
                    resultSet.getBigDecimal("product_price"),
                    resultSet.getInt("stock"),
                    resultSet.getString("branch_id"),
                    resultSet.getBoolean("is_active")
            ));
        }

        return productPrices;
    }
}
