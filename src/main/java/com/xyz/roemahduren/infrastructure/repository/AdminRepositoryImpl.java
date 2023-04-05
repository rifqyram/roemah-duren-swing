package com.xyz.roemahduren.infrastructure.repository;

import com.xyz.roemahduren.domain.entity.Admin;
import com.xyz.roemahduren.domain.repository.AdminRepository;

import java.sql.Connection;

public class AdminRepositoryImpl extends CrudRepositoryImpl<Admin, String> implements AdminRepository {
    protected AdminRepositoryImpl(Connection connection) {
        super(Admin.class, connection);
    }

}
