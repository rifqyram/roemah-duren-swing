package com.xyz.roemahduren.domain.service;

import com.xyz.roemahduren.domain.entity.ProductPrice;
import com.xyz.roemahduren.domain.model.request.ProductPriceRequest;

import java.util.List;

public interface ProductPriceService {
    ProductPrice create(ProductPriceRequest request);
    ProductPrice getById(String id);
    List<ProductPrice> getByProductId(String productId);
    List<ProductPrice> getByProductId(String productId, boolean isValid);
    ProductPrice update(ProductPriceRequest request);

}
