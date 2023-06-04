package com.xyz.roemahduren.application.service;

import com.xyz.roemahduren.domain.entity.SupplierProduct;
import com.xyz.roemahduren.domain.model.request.SupplierProductRequest;
import com.xyz.roemahduren.domain.model.response.SupplierProductResponse;
import com.xyz.roemahduren.domain.repository.Persistence;
import com.xyz.roemahduren.domain.repository.SupplierProductRepository;
import com.xyz.roemahduren.domain.service.SupplierProductService;
import com.xyz.roemahduren.exception.NotFoundException;

import java.sql.Connection;
import java.util.List;

public class SupplierProductServiceImpl implements SupplierProductService {
    private final SupplierProductRepository supplierProductRepository;
    private final Connection connection;
    private final Persistence persistence;

    public SupplierProductServiceImpl(SupplierProductRepository supplierProductRepository, Connection connection, Persistence persistence) {
        this.supplierProductRepository = supplierProductRepository;
        this.connection = connection;
        this.persistence = persistence;
    }

    @Override
    public SupplierProductResponse create(SupplierProductRequest request) {
        return persistence.executeTransaction(connection, () -> {
            SupplierProduct save = supplierProductRepository.save(new SupplierProduct(
                    request.getId(),
                    request.getSupplierId(),
                    request.getProductName(),
                    request.getPrice(),
                    request.getStock()
            ));
            return new SupplierProductResponse(
                    save.getId(),
                    null,
                    save.getProductName(),
                    save.getPrice(),
                    save.getStock());
        });
    }

    @Override
    public SupplierProductResponse getById(String id) {
        return supplierProductRepository.getById(id).orElseThrow(() -> new NotFoundException(NotFoundException.SUPPLIER_PRODUCT_NOT_FOUND));
    }

    @Override
    public SupplierProduct get(String id) {
        return supplierProductRepository.findById(id).orElseThrow(() -> new NotFoundException(NotFoundException.SUPPLIER_PRODUCT_NOT_FOUND));
    }

    @Override
    public List<SupplierProductResponse> getAll() {
        return supplierProductRepository.getAll();
    }

    @Override
    public SupplierProductResponse update(SupplierProductRequest request) {
        return persistence.executeTransaction(connection, () -> {
            findByIdOrThrowNotFound(request.getId());
            SupplierProduct update = supplierProductRepository.update(new SupplierProduct(
                    request.getId(),
                    request.getSupplierId(),
                    request.getProductName(),
                    request.getPrice(),
                    request.getStock()
            ));
            return new SupplierProductResponse(
                    update.getId(),
                    null,
                    update.getProductName(),
                    update.getPrice(),
                    update.getStock());
        });
    }

    @Override
    public SupplierProductResponse updateWithoutTransaction(SupplierProductRequest request) {
        findByIdOrThrowNotFound(request.getId());
        SupplierProduct update = supplierProductRepository.update(new SupplierProduct(
                request.getId(),
                request.getSupplierId(),
                request.getProductName(),
                request.getPrice(),
                request.getStock()
        ));
        return new SupplierProductResponse(
                update.getId(),
                null,
                update.getProductName(),
                update.getPrice(),
                update.getStock());
    }


    @Override
    public void deleteById(String id) {
        persistence.executeTransaction(connection, () -> {
            SupplierProduct supplierProduct = findByIdOrThrowNotFound(id);
            supplierProductRepository.deleteById(supplierProduct.getId());
        });
    }

    private SupplierProduct findByIdOrThrowNotFound(String id) {
        return supplierProductRepository.findById(id).orElseThrow(() -> new NotFoundException(NotFoundException.SUPPLIER_PRODUCT_NOT_FOUND));
    }
}
