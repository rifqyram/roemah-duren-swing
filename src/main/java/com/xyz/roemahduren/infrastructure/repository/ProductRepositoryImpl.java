package com.xyz.roemahduren.infrastructure.repository;

import com.xyz.roemahduren.domain.entity.Product;
import com.xyz.roemahduren.domain.model.response.ProductResponse;
import com.xyz.roemahduren.domain.repository.ProductRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryImpl extends CrudRepositoryImpl<Product, String> implements ProductRepository {
    private final Connection connection;

    public ProductRepositoryImpl(Connection connection) {
        super(Product.class, connection);
        this.connection = connection;
    }

    @Override
    public List<ProductResponse> findAllByName(String name) {
        String sql = "SELECT mp.id        as product_id,\n" +
                "       msp.product_name      as product_name,\n" +
                "       mp.price     as price,\n" +
                "       mc.name      as category_name,\n" +
                "       mp.stock     as stock,\n" +
                "       mp.is_active as is_active,\n" +
                "       mp.stock     as stock,\n" +
                "       mp.is_active as is_active,\n" +
                "       mb.name      as branch_name\n" +
                "FROM m_product mp\n" +
                "         JOIN m_category mc on mc.id = mp.category_id\n" +
                "         JOIN m_branch mb on mb.id = mp.branch_id\n" +
                "         JOIN m_supplier_product msp on mp.supplier_product_id = msp.id " +
                "WHERE LOWER(msp.product_name) LIKE LOWER(?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + name.toLowerCase() + "%");
            ResultSet resultSet = statement.executeQuery();

            List<ProductResponse> products = new ArrayList<>();

            addProductResponse(resultSet, products);

            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<ProductResponse> getById(String id) {
        String sql = "SELECT mp.id        as product_id,\n" +
                "       msp.product_name      as product_name,\n" +
                "       mp.price     as price,\n" +
                "       mc.name      as category_name,\n" +
                "       mp.stock     as stock,\n" +
                "       mp.is_active as is_active,\n" +
                "       mp.stock     as stock,\n" +
                "       mp.is_active as is_active,\n" +
                "       mb.name      as branch_name\n" +
                "FROM m_product mp\n" +
                "         JOIN m_category mc on mc.id = mp.category_id\n" +
                "         JOIN m_branch mb on mb.id = mp.branch_id\n" +
                "         JOIN m_supplier_product msp on mp.supplier_product_id = msp.id " +
                "WHERE mp.id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            List<ProductResponse> productResponses = new ArrayList<>();

            addProductResponse(resultSet, productResponses);

            return productResponses.stream().findAny();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProductResponse> getAll() {
        String sql = "SELECT mp.id        as product_id,\n" +
                "       msp.product_name      as product_name,\n" +
                "       mp.price     as price,\n" +
                "       mc.name      as category_name,\n" +
                "       mp.stock     as stock,\n" +
                "       mp.is_active as is_active,\n" +
                "       mp.stock     as stock,\n" +
                "       mp.is_active as is_active,\n" +
                "       mb.name      as branch_name\n" +
                "FROM m_product mp\n" +
                "         JOIN m_category mc on mc.id = mp.category_id\n" +
                "         JOIN m_branch mb on mb.id = mp.branch_id\n" +
                "         JOIN m_supplier_product msp on mp.supplier_product_id = msp.id";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            List<ProductResponse> productResponses = new ArrayList<>();

            addProductResponse(resultSet, productResponses);

            return productResponses;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProductResponse> getAll(boolean isActive) {
        String sql = "SELECT mp.id        as product_id,\n" +
                "       msp.product_name      as product_name,\n" +
                "       mp.price     as price,\n" +
                "       mc.name      as category_name,\n" +
                "       mp.stock     as stock,\n" +
                "       mp.is_active as is_active,\n" +
                "       mp.stock     as stock,\n" +
                "       mp.is_active as is_active,\n" +
                "       mb.name      as branch_name\n" +
                "FROM m_product mp\n" +
                "         JOIN m_category mc on mc.id = mp.category_id\n" +
                "         JOIN m_branch mb on mb.id = mp.branch_id\n" +
                "         JOIN m_supplier_product msp on mp.supplier_product_id = msp.id " +
                "WHERE mp.is_active = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, isActive);
            ResultSet resultSet = statement.executeQuery();

            List<ProductResponse> productResponses = new ArrayList<>();

            addProductResponse(resultSet, productResponses);

            return productResponses;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addProductResponse(ResultSet resultSet, List<ProductResponse> productResponses) throws SQLException {
        while (resultSet.next()) {
            productResponses.add(new ProductResponse(
                    resultSet.getString("product_id"),
                    resultSet.getString("product_name"),
                    resultSet.getLong("price"),
                    resultSet.getString("category_name"),
                    resultSet.getInt("stock"),
                    resultSet.getString("branch_name"),
                    resultSet.getBoolean("is_active")
            ));
        }
    }
}
