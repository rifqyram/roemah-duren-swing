package com.xyz.roemahduren.domain.entity;

import com.xyz.roemahduren.domain.annotation.Column;
import com.xyz.roemahduren.domain.annotation.Id;
import com.xyz.roemahduren.domain.annotation.Table;

@Table(name = "m_product")
public class Product {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "category_id")
    private String categoryId;

    public Product() {
    }

    public Product(String id, String name, Integer stock, String categoryId) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.categoryId = categoryId;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", stock=" + stock +
                ", categoryId='" + categoryId + '\'' +
                '}';
    }
}
