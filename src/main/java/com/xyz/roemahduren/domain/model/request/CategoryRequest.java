package com.xyz.roemahduren.domain.model.request;

import com.xyz.roemahduren.domain.annotation.validation.NotBlank;
import com.xyz.roemahduren.domain.entity.Category;
import com.xyz.roemahduren.util.RandomGenerator;

public class CategoryRequest {

    private String id;

    @NotBlank
    private String name;

    public CategoryRequest() {
    }

    public CategoryRequest(String name) {
        this.id = RandomGenerator.generateUUID();
        this.name = name;
    }

    public CategoryRequest(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category toCategory() {
        return new Category(this.id, this.name);
    }
}
