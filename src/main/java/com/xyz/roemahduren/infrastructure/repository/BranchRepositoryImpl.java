package com.xyz.roemahduren.infrastructure.repository;

import com.xyz.roemahduren.domain.entity.Branch;
import com.xyz.roemahduren.domain.repository.BranchRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BranchRepositoryImpl extends CrudRepositoryImpl<Branch, String> implements BranchRepository {
    private final Connection connection;

    protected BranchRepositoryImpl(Connection connection) {
        super(Branch.class, connection);
        this.connection = connection;
    }

    @Override
    public List<Branch> findByName(String name) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM m_branch WHERE name LIKE ?")) {
            statement.setString(1, name.toLowerCase() + "%");
            ResultSet resultSet = statement.executeQuery();

            List<Branch> branches = new ArrayList<>();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String nameRs = resultSet.getString("name");
                String address = resultSet.getString("address");
                String mobilePhone = resultSet.getString("mobile_phone");
                branches.add(new Branch(id, nameRs, address, mobilePhone));
            }
            return branches;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Branch> findByMobilePhone(String mobilePhone) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM m_branch WHERE mobile_phone = ?")) {
            statement.setString(1, mobilePhone);
            ResultSet resultSet = statement.executeQuery();

            List<Branch> branches = new ArrayList<>();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String nameRs = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("mobile_phone");
                branches.add(new Branch(id, nameRs, address, phone));
            }
            return branches.stream().findFirst();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
