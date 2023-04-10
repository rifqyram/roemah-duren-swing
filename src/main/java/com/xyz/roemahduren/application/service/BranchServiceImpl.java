package com.xyz.roemahduren.application.service;

import com.xyz.roemahduren.domain.entity.Branch;
import com.xyz.roemahduren.domain.model.request.BranchRequest;
import com.xyz.roemahduren.domain.repository.BranchRepository;
import com.xyz.roemahduren.domain.service.BranchService;
import com.xyz.roemahduren.exception.NotFoundException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final Connection connection;

    public BranchServiceImpl(BranchRepository branchRepository, Connection connection) {
        this.branchRepository = branchRepository;
        this.connection = connection;
    }

    @Override
    public Branch create(BranchRequest request) {
        try {
            Branch branch = new Branch(request.getId(), request.getName(), request.getAddress());
            Branch save = branchRepository.save(branch);
            connection.commit();
            return save;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Branch getById(String id) {
        return branchRepository.findById(id).orElseThrow(() -> new NotFoundException(NotFoundException.BRANCH_NOT_FOUND));
    }

    @Override
    public List<Branch> getAll() {
        return branchRepository.findAll();
    }

    @Override
    public Branch update(BranchRequest request) {
        getById(request.getId());
        Branch branch = new Branch(request.getId(), request.getName(), request.getAddress());
        return branchRepository.update(branch);
    }

    @Override
    public void deleteById(String id) {
        getById(id);
        branchRepository.deleteById(id);
    }
}
