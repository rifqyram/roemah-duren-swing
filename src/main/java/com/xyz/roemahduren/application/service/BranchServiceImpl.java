package com.xyz.roemahduren.application.service;

import com.xyz.roemahduren.domain.entity.Branch;
import com.xyz.roemahduren.domain.model.request.BranchRequest;
import com.xyz.roemahduren.domain.repository.BranchRepository;
import com.xyz.roemahduren.domain.repository.Persistence;
import com.xyz.roemahduren.domain.service.BranchService;
import com.xyz.roemahduren.exception.NotFoundException;

import java.sql.Connection;
import java.lang.Exception;
import java.util.List;
import java.util.Optional;

public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final Connection connection;
    private final Persistence persistence;

    public BranchServiceImpl(BranchRepository branchRepository, Connection connection, Persistence persistence) {
        this.branchRepository = branchRepository;
        this.connection = connection;
        this.persistence = persistence;
    }

    @Override
    public Branch create(BranchRequest request) {
        try {
            Optional<Branch> currentBranch = branchRepository.findByMobilePhone(request.getMobilePhone());

            if (currentBranch.isPresent()) {
                throw new RuntimeException("Cabang sudah terdaftar");
            }

            return persistence.executeTransaction(connection, () -> {
                Branch branch = new Branch(request.getId(), request.getName(), request.getAddress(), request.getMobilePhone());
                return branchRepository.save(branch);
            });
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
        return persistence.executeTransaction(connection, () -> {
            getById(request.getId());
            Branch branch = new Branch(request.getId(), request.getName(), request.getAddress(), request.getMobilePhone());
            return branchRepository.update(branch);
        });
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
