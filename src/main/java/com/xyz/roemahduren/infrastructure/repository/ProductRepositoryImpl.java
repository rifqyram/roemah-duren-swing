package com.xyz.roemahduren.infrastructure.repository;

import com.xyz.roemahduren.domain.entity.Product;
import com.xyz.roemahduren.domain.repository.ProductRepository;

import java.sql.Connection;

public class ProductRepositoryImpl extends CrudRepositoryImpl<Product, String> implements ProductRepository {

    protected ProductRepositoryImpl(Connection connection) {
        super(Product.class, connection);
    }

}
