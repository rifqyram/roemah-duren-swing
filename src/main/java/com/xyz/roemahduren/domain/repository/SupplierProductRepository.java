package com.xyz.roemahduren.domain.repository;

import com.xyz.roemahduren.domain.entity.SupplierProduct;
import com.xyz.roemahduren.domain.model.response.SupplierProductResponse;

import java.util.List;
import java.util.Optional;

public interface SupplierProductRepository extends CrudRepository<SupplierProduct, String> {

    Optional<SupplierProductResponse> getById(String id);
    List<SupplierProductResponse> getAll();

}
