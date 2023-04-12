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
    public Optional<Product> findByName(String name) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM m_product WHERE LOWER(name) = LOWER(?)")) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("category_id"),
                        resultSet.getBoolean("is_valid")
                ));
            }

            return products.stream().findFirst();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<ProductResponse> getById(String id) {
        String sql = "SELECT\n" +
                "    mp.id as product_id,\n" +
                "    mp.name as product_name,\n" +
                "    mc.name as category_name,\n" +
                "    mpp.product_price as product_price,\n" +
                "    mpp.stock as stock,\n" +
                "    mpp.is_active as is_valid,\n" +
                "    mpp.stock as stock,\n" +
                "    mpp.is_active as is_valid,\n" +
                "    mb.name as branch_name\n" +
                "FROM m_product mp\n" +
                "JOIN m_category mc on mc.id = mp.category_id\n" +
                "JOIN m_product_price mpp on mp.id = mpp.product_id\n" +
                "JOIN m_branch mb on mb.id = mpp.branch_id WHERE product_id = ?";

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
        String sql = "SELECT\n" +
                "    mp.id as product_id,\n" +
                "    mp.name as product_name,\n" +
                "    mc.name as category_name,\n" +
                "    mpp.product_price as product_price,\n" +
                "    mpp.stock as stock,\n" +
                "    mpp.is_active as is_valid,\n" +
                "    mpp.stock as stock,\n" +
                "    mpp.is_active as is_valid,\n" +
                "    mb.name as branch_name\n" +
                "FROM m_product mp\n" +
                "JOIN m_category mc on mc.id = mp.category_id\n" +
                "JOIN m_product_price mpp on mp.id = mpp.product_id\n" +
                "JOIN m_branch mb on mb.id = mpp.branch_id";

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
    public List<ProductResponse> getAll(boolean isValid) {
        String sql = "SELECT\n" +
                "    mp.id as product_id,\n" +
                "    mp.name as product_name,\n" +
                "    mc.name as category_name,\n" +
                "    mpp.product_price as product_price,\n" +
                "    mpp.stock as stock,\n" +
                "    mpp.is_active as is_valid,\n" +
                "    mpp.stock as stock,\n" +
                "    mpp.is_active as is_valid,\n" +
                "    mb.name as branch_name\n" +
                "FROM m_product mp\n" +
                "JOIN m_category mc on mc.id = mp.category_id\n" +
                "JOIN m_product_price mpp on mp.id = mpp.product_id\n" +
                "JOIN m_branch mb on mb.id = mpp.branch_id WHERE is_active = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, isValid);
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
                    resultSet.getBigDecimal("product_price"),
                    resultSet.getString("category_name"),
                    resultSet.getInt("stock"),
                    resultSet.getString("branch_name"),
                    resultSet.getBoolean("is_valid")
            ));
        }
    }
}
