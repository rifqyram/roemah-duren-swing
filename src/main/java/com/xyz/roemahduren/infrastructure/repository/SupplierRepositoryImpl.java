package com.xyz.roemahduren.infrastructure.repository;

import com.xyz.roemahduren.domain.entity.Supplier;
import com.xyz.roemahduren.domain.repository.SupplierRepository;

import java.sql.Connection;

public class SupplierRepositoryImpl extends CrudRepositoryImpl<Supplier, String> implements SupplierRepository {
    protected SupplierRepositoryImpl(Connection connection) {
        super(Supplier.class, connection);
    }
}
