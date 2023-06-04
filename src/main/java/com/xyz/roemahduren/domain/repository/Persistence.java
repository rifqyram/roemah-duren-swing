package com.xyz.roemahduren.domain.repository;

import com.xyz.roemahduren.util.MyFunction;

import java.sql.Connection;

public interface Persistence {
    <T> T executeTransaction(Connection connection, MyFunction<T> function);
    void executeTransaction(Connection connection, Runnable function);
}
