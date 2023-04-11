package com.xyz.roemahduren.application.service;

import com.xyz.roemahduren.domain.repository.BranchRepository;
import com.xyz.roemahduren.domain.repository.CategoryRepository;
import com.xyz.roemahduren.domain.repository.UserCredentialRepository;
import com.xyz.roemahduren.domain.service.AuthService;
import com.xyz.roemahduren.domain.service.BranchService;
import com.xyz.roemahduren.domain.service.CategoryService;
import com.xyz.roemahduren.infrastructure.security.PasswordEncoder;

import java.sql.Connection;

public class ServiceFactory {
    private final UserCredentialRepository userCredentialRepository;
    private final BranchRepository branchRepository;
    private final CategoryRepository categoryRepository;

    private final PasswordEncoder passwordEncoder;
    private final Connection connection;

    public ServiceFactory(UserCredentialRepository userCredentialRepository, BranchRepository branchRepository, CategoryRepository categoryRepository, PasswordEncoder passwordEncoder, Connection connection) {
        this.userCredentialRepository = userCredentialRepository;
        this.branchRepository = branchRepository;
        this.categoryRepository = categoryRepository;
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
}
