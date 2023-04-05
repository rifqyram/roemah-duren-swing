package com.xyz.roemahduren.infrastructure.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class ConnectionPool {
    private final Queue<Connection> freeConnections;
    private final String username;
    private final String password;
    private final String dbName;
    private final String dbHost;
    private final String dbPort;

    public ConnectionPool(int poolSize, String username, String password, String dbName, String dbHost, String dbPort) {
        freeConnections = new ArrayBlockingQueue<>(poolSize);
        this.username = username;
        this.password = password;
        this.dbName = dbName;
        this.dbHost = dbHost;
        this.dbPort = dbPort;
        for (int i = 0; i < poolSize; i++) {
            freeConnections.offer(createConnection());
        }
    }

    private Connection createConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = String.format("jdbc:mysql://%s:%s/%s?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC", dbHost, dbPort, dbName);
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection acquireConnection() throws InterruptedException {
        Connection connection = freeConnections.poll();
        if (connection == null) {
            connection = createConnection();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        freeConnections.offer(connection);
    }

    public void closeAllConnections() throws SQLException {
        Connection connection;
        while ((connection = freeConnections.poll()) != null) {
            connection.close();
        }
    }
}
