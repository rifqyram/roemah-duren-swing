package com.xyz.roemahduren.infrastructure.repository;

import com.xyz.roemahduren.domain.entity.SupplierProduct;
import com.xyz.roemahduren.domain.model.response.SupplierProductResponse;
import com.xyz.roemahduren.domain.repository.SupplierProductRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SupplierProductRepositoryImpl extends CrudRepositoryImpl<SupplierProduct, String> implements SupplierProductRepository {
    private final Connection connection;

    protected SupplierProductRepositoryImpl(Connection connection) {
        super(SupplierProduct.class, connection);
        this.connection = connection;
    }

    @Override
    public Optional<SupplierProductResponse> getById(String id) {
        String sql = "SELECT msp.id, ms.name, msp.product_name, msp.price, msp.stock, msp.init_stock FROM m_supplier_product msp\n" +
                "JOIN m_supplier ms on ms.id = msp.supplier_id WHERE msp.id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            List<SupplierProductResponse> supplierProductResponses = new ArrayList<>();

            getResultSetSupplierProductResponse(resultSet, supplierProductResponses);

            return supplierProductResponses.stream().findFirst();
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SupplierProductResponse> getAll() {
        String sql = "SELECT msp.id, ms.name, msp.product_name, msp.price, msp.stock, msp.init_stock FROM m_supplier_product msp\n" +
                "JOIN m_supplier ms on ms.id = msp.supplier_id";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<SupplierProductResponse> supplierProductResponses = new ArrayList<>();
            getResultSetSupplierProductResponse(resultSet, supplierProductResponses);
            return supplierProductResponses;
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void getResultSetSupplierProductResponse(ResultSet resultSet, List<SupplierProductResponse> supplierProductResponses) throws SQLException {
        while (resultSet.next()) {
            supplierProductResponses.add(new SupplierProductResponse(
                    resultSet.getString("id"),
                    resultSet.getString("name"),
                    resultSet.getString("product_name"),
                    resultSet.getLong("price"),
                    resultSet.getInt("stock"),
                    resultSet.getInt("init_stock")
            ));
        }
    }
}
