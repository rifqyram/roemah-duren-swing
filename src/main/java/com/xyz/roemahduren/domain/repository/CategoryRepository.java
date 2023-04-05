package com.xyz.roemahduren.domain.repository;

import com.xyz.roemahduren.domain.entity.Category;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, String> {

    Optional<Category> findByName(String name);

}
