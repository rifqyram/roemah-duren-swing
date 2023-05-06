package com.xyz.roemahduren.domain.service;

import com.xyz.roemahduren.domain.entity.Category;
import com.xyz.roemahduren.domain.model.request.CategoryRequest;

import java.util.List;

public interface CategoryService {

    Category create(CategoryRequest request);
    Category getById(String id);
    List<Category> getAll();
    Category update(CategoryRequest request);
    void deleteById(String id);

    List<Category> getAllByName(String value);
}
