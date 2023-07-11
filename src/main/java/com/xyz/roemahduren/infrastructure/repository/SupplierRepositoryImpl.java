package com.xyz.roemahduren.infrastructure.repository;

import com.xyz.roemahduren.domain.entity.Supplier;
import com.xyz.roemahduren.domain.repository.SupplierRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SupplierRepositoryImpl extends CrudRepositoryImpl<Supplier, String> implements SupplierRepository {
    private Connection connection;

    protected SupplierRepositoryImpl(Connection connection) {
        super(Supplier.class, connection);
        this.connection = connection;
    }

    @Override
    public Optional<Supplier> findByMobilePhone(String mobilePhone) {
        String sql = "SELECT * FROM m_supplier WHERE mobile_phone = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, mobilePhone);
            ResultSet resultSet = statement.executeQuery();

            List<Supplier> suppliers = new ArrayList<>();
            while (resultSet.next()) {
                suppliers.add(new Supplier(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("mobile_phone")
                ));
            }
            return suppliers.stream().findFirst();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
