package com.xyz.roemahduren.infrastructure.repository;

import com.xyz.roemahduren.domain.entity.Category;
import com.xyz.roemahduren.domain.repository.CategoryRepository;
import com.xyz.roemahduren.infrastructure.config.ConnectionPool;
import com.xyz.roemahduren.util.ReflectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryRepositoryImpl extends CrudRepositoryImpl<Category, String> implements CategoryRepository {
    private final Connection connection;

    protected CategoryRepositoryImpl(Connection connection) {
        super(Category.class, connection);
        this.connection = connection;
    }

    @Override
    public Optional<Category> findByName(String name) {
        try {
            String tableName = ReflectionUtil.getTableName(Category.class);
            String sql = String.format("SELECT id, name FROM %s WHERE name = LOWER(?)", tableName);

            PreparedStatement statement = connection.prepareStatement(sql);
            String s = name.toLowerCase();
            statement.setObject(1, s);

            ResultSet resultSet = statement.executeQuery();

            List<Category> categories = new ArrayList<>();

            if (resultSet.next()) {
                Category category = new Category(resultSet.getString("id"), resultSet.getString("name"));
                categories.add(category);
            }

            statement.close();
            return categories.stream().findFirst();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Category> findAllByNameIsLike(String name) {
        try {
            String tableName = ReflectionUtil.getTableName(Category.class);
            String sql = String.format("SELECT id, name FROM %s WHERE name LIKE ?", tableName);

            PreparedStatement statement = connection.prepareStatement(sql);
            String s = name.toLowerCase();
            statement.setObject(1, "%" + s + "%");

            ResultSet resultSet = statement.executeQuery();

            List<Category> categories = new ArrayList<>();

            while (resultSet.next()) {
                Category category = new Category(resultSet.getString("id"), resultSet.getString("name"));
                categories.add(category);
            }

            statement.close();
            return categories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
