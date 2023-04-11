package com.xyz.roemahduren.domain.service;

import com.xyz.roemahduren.domain.entity.Branch;
import com.xyz.roemahduren.domain.model.request.BranchRequest;

import java.util.List;

public interface BranchService {

    Branch create(BranchRequest request);
    Branch getById(String id);
    List<Branch> getAll();
    List<Branch> getByName(String name);
    Branch update(BranchRequest request);
    void deleteById(String id);

}
