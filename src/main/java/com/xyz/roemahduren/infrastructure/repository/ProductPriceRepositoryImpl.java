package com.xyz.roemahduren.infrastructure.repository;

import com.xyz.roemahduren.domain.entity.ProductPrice;
import com.xyz.roemahduren.domain.repository.ProductPriceRepository;

import java.sql.Connection;

public class ProductPriceRepositoryImpl extends CrudRepositoryImpl<ProductPrice, String> implements ProductPriceRepository {
    protected ProductPriceRepositoryImpl(Connection connection) {
        super(ProductPrice.class, connection);
    }
}
