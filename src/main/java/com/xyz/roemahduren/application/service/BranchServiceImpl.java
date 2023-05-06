package com.xyz.roemahduren.application.service;

import com.xyz.roemahduren.domain.entity.Branch;
import com.xyz.roemahduren.domain.model.request.BranchRequest;
import com.xyz.roemahduren.domain.repository.BranchRepository;
import com.xyz.roemahduren.domain.service.BranchService;
import com.xyz.roemahduren.exception.NotFoundException;

import java.sql.Connection;
import java.lang.Exception;
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
        } catch (Exception e) {
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
    public List<Branch> getByName(String name) {
        return branchRepository.findByName(name);
    }

    @Override
    public Branch update(BranchRequest request) {
        try {
            getById(request.getId());
            Branch branch = new Branch(request.getId(), request.getName(), request.getAddress());
            Branch update = branchRepository.update(branch);
            connection.commit();
            return update;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        try {
            getById(id);
            branchRepository.deleteById(id);
            connection.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
