package com.xyz.roemahduren.infrastructure.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ConnectionPoolThreadSafe {
    private static final int INITIAL_POOL_SIZE = 10;

    private final String username;
    private final String password;
    private final String dbName;
    private final String dbHost;
    private final String dbPort;
    private final List<Connection> connectionPool;
    private final List<Connection> usedConnections = Collections.synchronizedList(new LinkedList<>());

    public ConnectionPoolThreadSafe(String username, String password, String dbName, String dbHost, String dbPort) throws SQLException {
        this.username = username;
        this.password = password;
        this.dbName = dbName;
        this.dbHost = dbHost;
        this.dbPort = dbPort;
        connectionPool = Collections.synchronizedList(new LinkedList<>());

        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            connectionPool.add(createConnection());
        }
    }

    public synchronized Connection getConnection() throws SQLException {
        if (connectionPool.isEmpty()) {
            return createConnection();
        } else {
            Connection connection = connectionPool.remove(0);
            if (connection.isValid(1)) {
                return connection;
            } else {
                connection.close();
                return createConnection();
            }
        }
    }

    public synchronized void releaseConnection(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connectionPool.add(connection);
        }
    }

    public synchronized void closeAllConnections() throws SQLException {
        SQLException exception = null;
        for (Connection connection : connectionPool) {
            try {
                connection.close();
            } catch (SQLException e) {
                if (exception == null) {
                    exception = e;
                } else {
                    exception.addSuppressed(e);
                }
            }
        }
        connectionPool.clear();
        if (exception != null) {
            throw exception;
        }
    }

    private Connection createConnection() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%s/%s?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC", dbHost, dbPort, dbName);
        return DriverManager.getConnection(url, username, password);
    }

    public void closePool() throws SQLException {
        for (Connection connection : usedConnections) {
            connection.close();
        }
        for (Connection connection : connectionPool) {
            connection.close();
        }
        connectionPool.clear();
    }
}
