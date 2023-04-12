package com.xyz.roemahduren.application.service;

import com.xyz.roemahduren.domain.entity.Product;
import com.xyz.roemahduren.domain.entity.ProductPrice;
import com.xyz.roemahduren.domain.model.request.ProductPriceRequest;
import com.xyz.roemahduren.domain.model.request.ProductRequest;
import com.xyz.roemahduren.domain.model.response.ProductResponse;
import com.xyz.roemahduren.domain.repository.Persistence;
import com.xyz.roemahduren.domain.repository.ProductRepository;
import com.xyz.roemahduren.domain.service.ProductPriceService;
import com.xyz.roemahduren.domain.service.ProductService;
import com.xyz.roemahduren.exception.NotFoundException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final Connection connection;
    private final Persistence persistence;

    private final ProductPriceService productPriceService;

    public ProductServiceImpl(ProductRepository productRepository, Connection connection, Persistence persistence, ProductPriceService productPriceService) {
        this.productRepository = productRepository;
        this.connection = connection;
        this.persistence = persistence;
        this.productPriceService = productPriceService;
    }

    @Override
    public ProductResponse create(ProductRequest request) {
        Product product = new Product(
                request.getId(),
                request.getName(),
                request.getCategoryId(),
                true
        );

        Optional<Product> currentProductOptional = productRepository.findByName(product.getName());

        if (!currentProductOptional.isPresent()) {
            return persistence.executeTransaction(connection, () -> {
                ProductPrice productPrice = productPriceService.create(request.getProductPriceRequest());
                request.getProductPriceRequest().setProductId(productPrice.getId());
                ProductPrice newProductPrice = productPriceService.create(request.getProductPriceRequest());
                Product savedProduct = productRepository.save(product);
                return getProductResponse(request, savedProduct, newProductPrice);
            });
        }

        return persistence.executeTransaction(connection, () -> {
            Product currentProduct = currentProductOptional.get();
            request.getProductPriceRequest().setProductId(currentProduct.getId());
            ProductPrice productPrice = productPriceService.create(request.getProductPriceRequest());
            return getProductResponse(request, currentProduct, productPrice);
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
        Product product = get(request.getId());

        if (!request.getActive()) {
            List<ProductPrice> productPrices = productPriceService.getByProductId(product.getId());

            if (productPrices.size() > 1)
                throw new RuntimeException("Deactivated product is not valid, because it is related to the price of another product");

            return persistence.executeTransaction(connection, () -> {
                product.setActive(request.getActive());
                Product productUpdate = productRepository.update(product);

                ProductPriceRequest productPriceRequest = request.getProductPriceRequest();
                productPriceRequest.setProductId(product.getId());
                productPriceRequest.setActive(request.getActive());

                ProductPrice productPriceUpdate = productPriceService.update(productPriceRequest);
                return getProductResponse(request, productUpdate, productPriceUpdate);
            });
        }

        return persistence.executeTransaction(connection, () -> {
            product.setCategoryId(request.getCategoryId());
            product.setName(request.getName());
            product.setActive(request.getActive());

            Product productUpdate = productRepository.update(product);
            request.getProductPriceRequest().setProductId(product.getId());
            ProductPrice productPriceUpdate = productPriceService.update(request.getProductPriceRequest());
            return getProductResponse(request, productUpdate, productPriceUpdate);
        });
    }

    @Override
    public void deleteById(String id) {
        Product product = get(id);

        List<ProductPrice> productPrices = productPriceService.getByProductId(product.getId());
        if (productPrices.size() > 1)
            throw new RuntimeException("Deactivated product is not valid, because it is related to the price of another product");

        Optional<ProductPrice> productPriceOptional = productPrices.stream().findFirst();
        if (!productPriceOptional.isPresent()) throw new NotFoundException(NotFoundException.PRODUCT_PRICE_NOT_FOUND);
        ProductPrice productPrice = productPriceOptional.get();

        product.setActive(false);
        persistence.executeTransaction(connection, () -> {
            productRepository.update(product);
            productPriceService.update(new ProductPriceRequest(
                    productPrice.getId(),
                    productPrice.getProductPrice(),
                    productPrice.getStock(),
                    productPrice.getProductId(),
                    productPrice.getBranchId(),
                    false
            ));
            return product;
        });
    }

    private Product get(String id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException(NotFoundException.PRODUCT_NOT_FOUND));
    }

    private static ProductResponse getProductResponse(ProductRequest request, Product savedProduct, ProductPrice productPrice) {
        return new ProductResponse(
                savedProduct.getId(),
                request.getName(),
                productPrice.getProductPrice(),
                request.getCategoryId(),
                productPrice.getStock(),
                productPrice.getBranchId(),
                productPrice.getActive()
        );
    }
}
