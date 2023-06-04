package com.xyz.roemahduren.domain.service;

import com.xyz.roemahduren.domain.entity.Customer;
import com.xyz.roemahduren.domain.model.request.CustomerRequest;

public interface CustomerService {
    Customer getOrSave(CustomerRequest customer);
    Customer getById(String id);
}
