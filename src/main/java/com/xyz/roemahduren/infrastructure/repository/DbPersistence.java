package com.xyz.roemahduren.infrastructure.repository;

import com.xyz.roemahduren.domain.repository.Persistence;
import com.xyz.roemahduren.util.MyFunction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class DbPersistence implements Persistence {

    private static Persistence instance;

    public static synchronized Persistence getInstance() {
        if (instance == null) {
            instance = new DbPersistence();
        }
        return instance;
    }

    @Override
    public <T> T executeTransaction(Connection connection, MyFunction<T> function) {
        T result;

        try {
            connection.setAutoCommit(false);

            try {
                result = function.run();
                connection.commit();
            } catch (RuntimeException | SQLException exception) {
                connection.rollback();
                throw new RuntimeException(exception);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public void executeTransaction(Connection connection, Runnable function) {
        try {
            connection.setAutoCommit(false);

            try {
                function.run();
                connection.commit();
            } catch (RuntimeException | SQLException exception) {
                connection.rollback();
                if (!Objects.isNull(exception.getMessage())) throw new RuntimeException(exception.getMessage());
                throw new RuntimeException(exception);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
