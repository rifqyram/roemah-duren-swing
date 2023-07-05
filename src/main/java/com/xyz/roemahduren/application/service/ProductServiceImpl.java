package com.xyz.roemahduren.application.service;

import com.xyz.roemahduren.domain.entity.Product;
import com.xyz.roemahduren.domain.entity.SupplierProduct;
import com.xyz.roemahduren.domain.model.request.ProductRequest;
import com.xyz.roemahduren.domain.model.request.SupplierProductRequest;
import com.xyz.roemahduren.domain.model.response.ProductResponse;
import com.xyz.roemahduren.domain.model.response.SupplierProductResponse;
import com.xyz.roemahduren.domain.repository.Persistence;
import com.xyz.roemahduren.domain.repository.ProductRepository;
import com.xyz.roemahduren.domain.service.ProductService;
import com.xyz.roemahduren.domain.service.SupplierProductService;
import com.xyz.roemahduren.exception.NotFoundException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final Connection connection;
    private final Persistence persistence;
    private final SupplierProductService supplierProductService;


    public ProductServiceImpl(ProductRepository productRepository, Connection connection, Persistence persistence, SupplierProductService supplierProductService) {
        this.productRepository = productRepository;
        this.connection = connection;
        this.persistence = persistence;
        this.supplierProductService = supplierProductService;
    }

    @Override
    public ProductResponse create(ProductRequest request) {
        return persistence.executeTransaction(connection, () -> {
            Product product = new Product(request.getId(), request.getSupplierProductId(), request.getPrice(), request.getStock(), request.getBranchId(), request.getCategoryId(), true);
            SupplierProduct supplierProduct = supplierProductService.get(product.getSupplierProductId());
            supplierProductService.updateWithoutTransaction(new SupplierProductRequest(
                    supplierProduct.getId(),
                    supplierProduct.getSupplierId(),
                    supplierProduct.getProductName(),
                    supplierProduct.getPrice(),
                    supplierProduct.getStock() - product.getStock(),
                    supplierProduct.getInitStock()
            ));
            productRepository.save(product);
            return new ProductResponse(product.getId(), product.getSupplierProductId(), product.getPrice(), null, product.getStock(), null, product.getActive());
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
    public List<ProductResponse> getAllWithAvailableStock(boolean isValid) {
        return productRepository.getAllAvailableStock(isValid);
    }

    @Override
    public ProductResponse update(ProductRequest request) {
        return persistence.executeTransaction(connection, () -> {
            Product currentProduct = get(request.getId());
            Product product = new Product(request.getId(), request.getSupplierProductId(), request.getPrice(), request.getStock(), request.getBranchId(), request.getCategoryId(), request.getActive());
            SupplierProduct supplierProduct = supplierProductService.get(product.getSupplierProductId());
            int diffStock = 0;

            if (request.getStock() > product.getStock()) {
                int result = request.getStock() - currentProduct.getStock();
                diffStock = supplierProduct.getStock() - result;
            } else {
                int result = currentProduct.getStock() - request.getStock();
                diffStock = supplierProduct.getStock() + result;
            }

            supplierProductService.updateWithoutTransaction(new SupplierProductRequest(
                    supplierProduct.getId(),
                    supplierProduct.getSupplierId(),
                    supplierProduct.getProductName(),
                    supplierProduct.getPrice(),
                    diffStock,
                    supplierProduct.getInitStock()
            ));
            productRepository.update(product);
            return new ProductResponse(product.getId(), product.getSupplierProductId(), product.getPrice(), null, product.getStock(), null, product.getActive());
        });
    }

    @Override
    public ProductResponse updateWithoutTransaction(ProductRequest request) {
        Product product = new Product(request.getId(), request.getSupplierProductId(), request.getPrice(), request.getStock(), request.getBranchId(), request.getCategoryId(), request.getActive());
        productRepository.update(product);
        return new ProductResponse(product.getId(), product.getSupplierProductId(), product.getPrice(), null, product.getStock(), null, product.getActive());
    }


    @Override
    public void deleteById(String id) {
        persistence.executeTransaction(connection, () -> {
            Product product = get(id);
            SupplierProduct supplierProduct = supplierProductService.get(product.getSupplierProductId());
            supplierProductService.update(new SupplierProductRequest(
                    supplierProduct.getId(),
                    supplierProduct.getSupplierId(),
                    supplierProduct.getProductName(),
                    supplierProduct.getPrice(),
                    supplierProduct.getStock() + product.getStock()
            ));
            productRepository.deleteById(product.getId());
        });
    }

    @Override
    public List<ProductResponse> getAllByName(String value) {
        return productRepository.findAllByName(value);
    }

    public Product get(String id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException(NotFoundException.PRODUCT_NOT_FOUND));
    }

}
