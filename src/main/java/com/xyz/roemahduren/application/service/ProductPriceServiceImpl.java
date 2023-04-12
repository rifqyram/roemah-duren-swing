package com.xyz.roemahduren.application.service;

import com.xyz.roemahduren.domain.entity.ProductPrice;
import com.xyz.roemahduren.domain.model.request.ProductPriceRequest;
import com.xyz.roemahduren.domain.repository.ProductPriceRepository;
import com.xyz.roemahduren.domain.service.ProductPriceService;
import com.xyz.roemahduren.exception.NotFoundException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductPriceServiceImpl implements ProductPriceService {

    private final ProductPriceRepository productPriceRepository;
    private final Connection connection;

    public ProductPriceServiceImpl(ProductPriceRepository productPriceRepository, Connection connection) {
        this.productPriceRepository = productPriceRepository;
        this.connection = connection;
    }

    @Override
    public ProductPrice create(ProductPriceRequest request) {
        Optional<ProductPrice> currentProductPriceOptional = getByProductId(request.getProductId(), true).stream().findFirst();

        try {
            ProductPrice productPrice = new ProductPrice(
                    request.getId(),
                    request.getProductId(),
                    request.getPrice(),
                    request.getStock(),
                    request.getBranchId(),
                    true
            );

            if (!currentProductPriceOptional.isPresent()) {
                ProductPrice save = productPriceRepository.save(productPrice);
                if (save == null) connection.rollback();
                return save;
            }

            ProductPrice currentProductPrice = currentProductPriceOptional.get();
            ProductPrice savedProductPrice = productPriceRepository.save(productPrice);
            currentProductPrice.setActive(false);

            ProductPrice update = productPriceRepository.update(currentProductPrice);
            if (request.getBranchId().equals(currentProductPrice.getBranchId()) &&
                    request.getPrice().equals(currentProductPrice.getProductPrice())) connection.rollback();
            if (update == null || savedProductPrice == null) connection.rollback();

            return savedProductPrice;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductPrice getById(String id) {
        return productPriceRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(NotFoundException.PRODUCT_PRICE_NOT_FOUND));
    }

    @Override
    public List<ProductPrice> getByProductId(String productId) {
        return productPriceRepository.findAllByProductId(productId);
    }

    @Override
    public List<ProductPrice> getByProductId(String productId, boolean isValid) {
        return productPriceRepository.findAllByProductId(productId, isValid);
    }

    @Override
    public ProductPrice update(ProductPriceRequest request) {
        try {
            ProductPrice productPrice = getById(request.getId());

            if (!productPrice.getBranchId().equals(request.getBranchId())) {
                connection.rollback();
                throw new RuntimeException("Branch cannot be changed");
            }

            if (!productPrice.getProductId().equals(request.getProductId())) {
                connection.rollback();
                throw new RuntimeException("Product cannot be changed");
            }

            productPrice.setProductPrice(request.getPrice());
            productPrice.setActive(request.getActive());
            productPrice.setStock(request.getStock());

            return productPriceRepository.update(productPrice);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
