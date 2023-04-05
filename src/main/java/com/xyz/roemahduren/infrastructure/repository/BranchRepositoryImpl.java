package com.xyz.roemahduren.infrastructure.repository;

import com.xyz.roemahduren.domain.entity.Branch;
import com.xyz.roemahduren.domain.repository.BranchRepository;

import java.sql.Connection;

public class BranchRepositoryImpl extends CrudRepositoryImpl<Branch, String> implements BranchRepository {
    protected BranchRepositoryImpl(Connection connection) {
        super(Branch.class, connection);
    }
}
