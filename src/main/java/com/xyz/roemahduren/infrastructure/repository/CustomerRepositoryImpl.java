package com.xyz.roemahduren.infrastructure.repository;

import com.xyz.roemahduren.domain.entity.Customer;
import com.xyz.roemahduren.domain.repository.CustomerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerRepositoryImpl extends CrudRepositoryImpl<Customer, String> implements CustomerRepository {
    private final Connection connection;

    protected CustomerRepositoryImpl(Connection connection) {
        super(Customer.class, connection);
        this.connection = connection;
    }


    @Override
    public Optional<Customer> findByCustomerName(String name) {
        String sql = "SELECT * FROM m_customer WHERE LOWER(name) = LOWER(?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            List<Customer> customers = new ArrayList<>();
            while (resultSet.next()) {
                customers.add(new Customer(
                    resultSet.getString("id"),
                    resultSet.getString("name"),
                    resultSet.getString("mobile_phone"),
                    resultSet.getString("address")
                ));
            }

            return customers.stream().findFirst();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
