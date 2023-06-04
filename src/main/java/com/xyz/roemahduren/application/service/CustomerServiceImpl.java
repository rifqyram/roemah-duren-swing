package com.xyz.roemahduren.application.service;

import com.xyz.roemahduren.domain.entity.Customer;
import com.xyz.roemahduren.domain.model.request.CustomerRequest;
import com.xyz.roemahduren.domain.repository.CustomerRepository;
import com.xyz.roemahduren.domain.service.CustomerService;
import com.xyz.roemahduren.exception.NotFoundException;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getOrSave(CustomerRequest request) {
        return customerRepository.findByCustomerName(request.getName()).orElseGet(() -> {
            Customer customer = new Customer(request.getId(), request.getName(), request.getMobilePhone(), request.getAddress());
            return customerRepository.save(customer);
        });
    }

    @Override
    public Customer getById(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new NotFoundException(NotFoundException.CUSTOMER_NOT_FOUND));
    }
}
