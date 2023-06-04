package com.xyz.roemahduren.application.service;

import com.xyz.roemahduren.domain.entity.Product;
import com.xyz.roemahduren.domain.model.request.ProductRequest;
import com.xyz.roemahduren.domain.model.response.ProductResponse;
import com.xyz.roemahduren.domain.repository.Persistence;
import com.xyz.roemahduren.domain.repository.ProductRepository;
import com.xyz.roemahduren.domain.service.ProductService;
import com.xyz.roemahduren.exception.NotFoundException;

import java.sql.Connection;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final Connection connection;
    private final Persistence persistence;


    public ProductServiceImpl(ProductRepository productRepository, Connection connection, Persistence persistence) {
        this.productRepository = productRepository;
        this.connection = connection;
        this.persistence = persistence;
    }

    @Override
    public ProductResponse create(ProductRequest request) {
        return persistence.executeTransaction(connection, () -> {
            Product product = new Product(request.getId(), request.getName(), request.getPrice(), request.getStock(), request.getBranchId(), request.getCategoryId(), true);
            productRepository.save(product);
            return new ProductResponse(product.getId(), product.getName(), product.getPrice(), null, product.getStock(), null, product.getActive());
        });
    }

    @Override
    public ProductResponse getById(String id) {
        return productRepository.getById(id).orElseThrow(() -> new NotFoundException(NotFoundException.PRODUCT_NOT_FOUND));
    }

    @Override
    public List<ProductResponse> getAll() {
        return productRepository.getAll();
    }

    @Override
    public List<ProductResponse> getAll(boolean isValid) {
        return productRepository.getAll(isValid);
    }

    @Override
    public ProductResponse update(ProductRequest request) {
        return persistence.executeTransaction(connection, () -> {
            Product product = new Product(request.getId(), request.getName(), request.getPrice(), request.getStock(), request.getBranchId(), request.getCategoryId(), request.getActive());
            productRepository.update(product);
            return new ProductResponse(product.getId(), product.getName(), product.getPrice(), null, product.getStock(), null, product.getActive());
        });
    }

    @Override
    public void deleteById(String id) {
        persistence.executeTransaction(connection, () -> {
            Product product = get(id);
            productRepository.deleteById(product.getId());
        });
    }

    @Override
    public List<ProductResponse> getAllByName(String value) {
        return productRepository.findAllByName(value);
    }

    private Product get(String id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException(NotFoundException.PRODUCT_NOT_FOUND));
    }

}
