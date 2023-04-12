package com.xyz.roemahduren.domain.repository;

import com.xyz.roemahduren.domain.entity.ProductPrice;

import java.util.List;

public interface ProductPriceRepository extends CrudRepository<ProductPrice, String> {
    List<ProductPrice> findAllByProductId(String productId);
    List<ProductPrice> findAllByProductId(String productId, boolean isValid);
}
