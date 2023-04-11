package com.xyz.roemahduren.domain.repository;

import com.xyz.roemahduren.domain.entity.Branch;

import java.util.List;

public interface BranchRepository extends CrudRepository<Branch, String> {
    List<Branch> findByName(String name);
}
