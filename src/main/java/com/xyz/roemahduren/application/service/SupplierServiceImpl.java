package com.xyz.roemahduren.application.service;

import com.xyz.roemahduren.domain.entity.Supplier;
import com.xyz.roemahduren.domain.model.request.SupplierRequest;
import com.xyz.roemahduren.domain.repository.Persistence;
import com.xyz.roemahduren.domain.repository.SupplierRepository;
import com.xyz.roemahduren.domain.service.SupplierService;
import com.xyz.roemahduren.exception.NotFoundException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final Connection connection;
    private final Persistence persistence;

    public SupplierServiceImpl(SupplierRepository supplierRepository, Connection connection, Persistence persistence) {
        this.supplierRepository = supplierRepository;
        this.connection = connection;
        this.persistence = persistence;
    }

    @Override
    public Supplier create(SupplierRequest request) {
        Optional<Supplier> supplier = supplierRepository.findByMobilePhone(request.getMobilePhone());

        if (supplier.isPresent()) {
            throw new RuntimeException("Nomor telepon pemasok sudah terdaftar");
        }

        return persistence.executeTransaction(connection, () -> supplierRepository.save(new Supplier(request.getId(), request.getName(), request.getAddress(), request.getMobilePhone())));
    }

    @Override
    public Supplier getById(String id) {
        return supplierRepository.findById(id).orElseThrow(() -> new NotFoundException(NotFoundException.SUPPLIER_NOT_FOUND));
    }

    @Override
    public List<Supplier> getAll() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier update(SupplierRequest request) {
        return persistence.executeTransaction(connection, () -> {
            Supplier supplier = getById(request.getId());
            return supplierRepository.update(new Supplier(supplier.getId(), request.getName(), request.getAddress(), request.getMobilePhone()));
        });
    }

    @Override
    public void deleteById(String id) {
        persistence.executeTransaction(connection, () -> {
            Supplier supplier = getById(id);
            supplierRepository.deleteById(supplier.getId());
        });
    }
}
