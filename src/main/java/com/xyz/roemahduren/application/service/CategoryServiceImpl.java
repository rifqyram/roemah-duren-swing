package com.xyz.roemahduren.application.service;

import com.xyz.roemahduren.domain.entity.Category;
import com.xyz.roemahduren.domain.model.request.CategoryRequest;
import com.xyz.roemahduren.domain.repository.CategoryRepository;
import com.xyz.roemahduren.domain.service.CategoryService;
import com.xyz.roemahduren.exception.NotFoundException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final Connection connection;

    public CategoryServiceImpl(CategoryRepository categoryRepository, Connection connection) {
        this.categoryRepository = categoryRepository;
        this.connection = connection;
    }

    @Override
    public Category create(CategoryRequest request) {
        try {
            Category category = request.toCategory();
            Category savedCategory = categoryRepository.save(category);
            connection.commit();
            return savedCategory;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category getById(String id) {
        return categoryRepository.findById(id).orElseThrow(() -> new NotFoundException(NotFoundException.CATEGORY_NOT_FOUND));
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category update(CategoryRequest request) {
        try {
            Category category = getById(request.getId());
            category.setName(request.getName());
            Category newCategory = categoryRepository.update(category);
            connection.commit();
            return newCategory;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        try {
            getById(id);
            categoryRepository.deleteById(id);
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
