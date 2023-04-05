package com.xyz.roemahduren.infrastructure.repository;

import com.xyz.roemahduren.domain.repository.CategoryRepository;
import com.xyz.roemahduren.domain.repository.Persistence;
import com.xyz.roemahduren.domain.repository.ProductRepository;
import com.xyz.roemahduren.infrastructure.config.ConnectionPool;

import java.sql.Connection;

public class RepositoryFactory {

    private final Connection connection;

    public RepositoryFactory(Connection connection) {
        this.connection = connection;
    }

    public Persistence persistence() {
        return DbPersistence.getInstance();
    }

    public CategoryRepository categoryRepository() {
        return new CategoryRepositoryImpl(connection);
    }

    public ProductRepository productRepository() {
        return new ProductRepositoryImpl(connection);
    }

}
