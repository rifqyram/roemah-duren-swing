package com.xyz.roemahduren.infrastructure.repository;

import com.xyz.roemahduren.domain.repository.*;

import java.sql.Connection;

public class RepositoryFactory {

    private final Connection connection;

    public RepositoryFactory(Connection connection) {
        this.connection = connection;
    }

    public Persistence persistence() {
        return DbPersistence.getInstance();
    }

    public AdminRepository adminRepository() {
        return new AdminRepositoryImpl(connection);
    }

    public BranchRepository branchRepository() {
        return new BranchRepositoryImpl(connection);
    }

    public CategoryRepository categoryRepository() {
        return new CategoryRepositoryImpl(connection);
    }

    public CustomerRepository customerRepository() {
        return new CustomerRepositoryImpl(connection);
    }

    public OrderDetailRepository orderDetailRepository() {
        return new OrderDetailRepositoryImpl(connection);
    }

    public OrderRepository orderRepository() {
        return new OrderRepositoryImpl(connection);
    }

    public ProductRepository productRepository() {
        return new ProductRepositoryImpl(connection);
    }

    public UserCredentialRepository userCredentialRepository() {
        return new UserCredentialRepositoryImpl(connection);
    }

}
