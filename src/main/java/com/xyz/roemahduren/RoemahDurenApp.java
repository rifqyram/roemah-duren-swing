package com.xyz.roemahduren;

import com.xyz.roemahduren.infrastructure.config.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class RoemahDurenApp {

    private static final String username = System.getenv("DB_USERNAME");
    private static final String password = System.getenv("DB_PASSWORD");
    private static final String dbName = System.getenv("DB_NAME");
    private static final String dbHost = System.getenv("DB_HOST");
    private static final String dbPort = System.getenv("DB_PORT");

    public static void main(String[] args) {
        ConnectionPool connectionPool = new ConnectionPool(10, username, password, dbName, dbHost, dbPort);

        try (Connection connection = connectionPool.acquireConnection();) {

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}