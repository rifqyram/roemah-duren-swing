package com.xyz.roemahduren.domain.repository;

import com.xyz.roemahduren.domain.entity.Branch;

import java.util.List;
import java.util.Optional;

public interface BranchRepository extends CrudRepository<Branch, String> {
    List<Branch> findByName(String name);
    Optional<Branch> findByMobilePhone(String mobilePhone);
}
