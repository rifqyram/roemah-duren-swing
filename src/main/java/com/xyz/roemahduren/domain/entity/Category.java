package com.xyz.roemahduren.domain.entity;

import com.xyz.roemahduren.domain.annotation.Column;
import com.xyz.roemahduren.domain.annotation.Id;
import com.xyz.roemahduren.domain.annotation.Table;

import java.util.Objects;

@Table(name = "m_category")
public class Category {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    public Category() {
    }

    public Category(String id, String name) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) && Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }


}
