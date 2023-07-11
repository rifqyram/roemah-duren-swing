package com.xyz.roemahduren.domain.repository;

import com.xyz.roemahduren.domain.entity.Product;
import com.xyz.roemahduren.domain.model.response.ProductResponse;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, String> {
    Optional<Product> findBySupplierProductIdAndBranchIdAndCategoryIdAndPrice(String supplierProductId, String branchId, String categoryId, Long price);
    List<ProductResponse> findAllByName(String name);
    Optional<ProductResponse> getById(String id);
    List<ProductResponse> getAll();
    List<ProductResponse> getAll(boolean isValid);
    List<ProductResponse> getAllAvailableStock(boolean isValid);
}
