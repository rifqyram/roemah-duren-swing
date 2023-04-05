package com.xyz.roemahduren.infrastructure.repository;

import com.xyz.roemahduren.domain.entity.Customer;
import com.xyz.roemahduren.domain.repository.CustomerRepository;

import java.sql.Connection;

public class CustomerRepositoryImpl extends CrudRepositoryImpl<Customer, String> implements CustomerRepository {
    protected CustomerRepositoryImpl(Connection connection) {
        super(Customer.class, connection);
    }
}
