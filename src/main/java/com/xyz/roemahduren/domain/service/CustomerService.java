package com.xyz.roemahduren.domain.service;

import com.xyz.roemahduren.domain.entity.Customer;
import com.xyz.roemahduren.domain.model.request.CustomerRequest;

import java.util.List;

public interface CustomerService {
    Customer getOrSave(CustomerRequest customer);
    Customer getById(String id);
    List<Customer> getAll();
}
