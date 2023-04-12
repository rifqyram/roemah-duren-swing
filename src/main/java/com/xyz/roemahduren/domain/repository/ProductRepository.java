package com.xyz.roemahduren.domain.repository;

import com.xyz.roemahduren.domain.entity.Product;
import com.xyz.roemahduren.domain.model.response.ProductResponse;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, String> {
    Optional<Product> findByName(String name);
    Optional<ProductResponse> getById(String id);
    List<ProductResponse> getAll();
    List<ProductResponse> getAll(boolean isValid);
}
