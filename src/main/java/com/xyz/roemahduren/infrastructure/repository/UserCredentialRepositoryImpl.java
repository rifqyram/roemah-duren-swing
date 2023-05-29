package com.xyz.roemahduren.infrastructure.repository;

import com.xyz.roemahduren.domain.entity.UserCredential;
import com.xyz.roemahduren.domain.repository.UserCredentialRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserCredentialRepositoryImpl extends CrudRepositoryImpl<UserCredential, String> implements UserCredentialRepository {

    private final Connection connection;

    protected UserCredentialRepositoryImpl(Connection connection) {
        super(UserCredential.class, connection);
        this.connection = connection;
    }

    @Override
    public Optional<UserCredential> findByEmail(String email) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT id, email, password FROM m_user_credential WHERE LOWER(email) = ?")) {
            statement.setString(1, email.toLowerCase());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(new UserCredential(resultSet.getString("id"), resultSet.getString("email"), resultSet.getString("password"), null));
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
