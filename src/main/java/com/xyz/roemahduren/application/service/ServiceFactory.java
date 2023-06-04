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
    private final CustomerRepository customerRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final SupplierRepository supplierRepository;
    private final SupplierProductRepository supplierProductRepository;
    private final Persistence persistence;

    private final PasswordEncoder passwordEncoder;
    private final Connection connection;

    public ServiceFactory(UserCredentialRepository userCredentialRepository, BranchRepository branchRepository, CategoryRepository categoryRepository, ProductRepository productRepository, CustomerRepository customerRepository, OrderDetailRepository orderDetailRepository, OrderRepository orderRepository, SupplierRepository supplierRepository, SupplierProductRepository supplierProductRepository, Persistence persistence, PasswordEncoder passwordEncoder, Connection connection) {
        this.userCredentialRepository = userCredentialRepository;
        this.branchRepository = branchRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
        this.supplierRepository = supplierRepository;
        this.supplierProductRepository = supplierProductRepository;
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

    public ProductService productService() {
        return new ProductServiceImpl(productRepository, connection, persistence, supplierProductService());
    }

    public CustomerService customerService() {
        return new CustomerServiceImpl(customerRepository);
    }

    public OrderDetailService orderDetailService() {
        return new OrderDetailServiceImpl(orderDetailRepository, productService());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(orderRepository, connection, persistence, orderDetailService(), customerService());
    }

    public SupplierService supplierService() {
        return new SupplierServiceImpl(supplierRepository, connection, persistence);
    }

    public SupplierProductService supplierProductService() {
        return new SupplierProductServiceImpl(supplierProductRepository, connection, persistence);
    }
}
