package com.xyz.roemahduren.infrastructure.repository;

import com.xyz.roemahduren.domain.entity.InvoiceNumber;
import com.xyz.roemahduren.domain.repository.InvoiceNumberRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InvoiceNumberRepositoryImpl extends CrudRepositoryImpl<InvoiceNumber, String> implements InvoiceNumberRepository {
    private Connection connection;

    protected InvoiceNumberRepositoryImpl(Connection connection) {
        super(InvoiceNumber.class, connection);
        this.connection = connection;
    }

    @Override
    public Optional<InvoiceNumber> findByInvoiceDate(Date date) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM m_invoice_number WHERE invoice_date = ?")) {
            statement.setDate(1, date);
            ResultSet resultSet = statement.executeQuery();

            List<InvoiceNumber> invoiceNumbers = new ArrayList<>();
            while (resultSet.next()) {
                invoiceNumbers.add(
                        new InvoiceNumber(
                                resultSet.getString("id"),
                                resultSet.getDate("invoice_date"),
                                resultSet.getLong("invoice_sequence")
                        )
                );
            }
            return invoiceNumbers.stream().findFirst();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
