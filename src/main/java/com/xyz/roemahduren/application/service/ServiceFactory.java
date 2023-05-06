package com.xyz.roemahduren.application.service;

import com.xyz.roemahduren.domain.repository.*;
import com.xyz.roemahduren.domain.service.*;
import com.xyz.roemahduren.infrastructure.security.PasswordEncoder;

import java.sql.Connection;

public class ServiceFactory {
    private final UserCredentialRepository userCredentialRepository;
    private final BranchRepository branchRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ProductPriceRepository productPriceRepository;
    private final Persistence persistence;

    private final PasswordEncoder passwordEncoder;
    private final Connection connection;

    public ServiceFactory(UserCredentialRepository userCredentialRepository, BranchRepository branchRepository, CategoryRepository categoryRepository, ProductRepository productRepository, ProductPriceRepository productPriceRepository, Persistence persistence, PasswordEncoder passwordEncoder, Connection connection) {
        this.userCredentialRepository = userCredentialRepository;
        this.branchRepository = branchRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.productPriceRepository = productPriceRepository;
        this.persistence = persistence;
        this.passwordEncoder = passwordEncoder;
        this.connection = connection;
    }

    public AuthService authService() {
        return new AuthServiceImpl(userCredentialRepository, passwordEncoder, connection);
    }

    public BranchService branchService() {
        return new BranchServiceImpl(branchRepository, connection);
    }

    public CategoryService categoryService() {
        return new CategoryServiceImpl(categoryRepository, connection);
    }

    public ProductPriceService productPriceService() {
        return new ProductPriceServiceImpl(productPriceRepository, connection);
    }

    public ProductService productService() {
        return new ProductServiceImpl(productRepository, connection, persistence, productPriceService());
    }
}
