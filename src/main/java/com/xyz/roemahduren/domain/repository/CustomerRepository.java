package com.xyz.roemahduren.domain.repository;

import com.xyz.roemahduren.domain.entity.Customer;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, String> {

    Optional<Customer> findByCustomerName(String name);
}
