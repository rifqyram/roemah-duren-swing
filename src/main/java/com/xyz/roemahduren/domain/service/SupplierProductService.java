package com.xyz.roemahduren.domain.service;

import com.xyz.roemahduren.domain.entity.SupplierProduct;
import com.xyz.roemahduren.domain.model.request.SupplierProductRequest;
import com.xyz.roemahduren.domain.model.response.SupplierProductResponse;

import java.util.List;

public interface SupplierProductService {

    SupplierProductResponse create(SupplierProductRequest request);
    SupplierProductResponse getById(String id);
    SupplierProduct get(String id);
    List<SupplierProductResponse> getAll();
    SupplierProductResponse update(SupplierProductRequest request);
    SupplierProductResponse updateWithoutTransaction(SupplierProductRequest request);
    void deleteById(String id);
}
