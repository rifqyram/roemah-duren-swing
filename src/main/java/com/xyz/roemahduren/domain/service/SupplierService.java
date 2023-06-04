package com.xyz.roemahduren.domain.service;

import com.xyz.roemahduren.domain.entity.Supplier;
import com.xyz.roemahduren.domain.model.request.SupplierRequest;

import java.util.List;

public interface SupplierService {

    Supplier create(SupplierRequest request);
    Supplier getById(String id);
    List<Supplier> getAll();
    Supplier update(SupplierRequest request);
    void deleteById(String id);

}
