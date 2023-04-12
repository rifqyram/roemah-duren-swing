package com.xyz.roemahduren.domain.service;

import com.xyz.roemahduren.domain.model.request.ProductRequest;
import com.xyz.roemahduren.domain.model.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse create(ProductRequest request);
    ProductResponse getById(String id);
    List<ProductResponse> getAll();
    List<ProductResponse> getAll(boolean isValid);
    ProductResponse update(ProductRequest request);
    void deleteById(String id);
}
