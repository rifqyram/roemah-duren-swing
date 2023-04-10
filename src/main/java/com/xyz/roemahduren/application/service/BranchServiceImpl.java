package com.xyz.roemahduren.application.service;

import com.xyz.roemahduren.domain.entity.Branch;
import com.xyz.roemahduren.domain.model.request.BranchRequest;
import com.xyz.roemahduren.domain.repository.BranchRepository;
import com.xyz.roemahduren.domain.service.BranchService;
import com.xyz.roemahduren.exception.NotFoundException;

import java.util.List;

public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public Branch create(BranchRequest request) {
        Branch branch = new Branch(request.getId(), request.getName(), request.getAddress());
        return branchRepository.save(branch);
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
