package com.xyz.roemahduren.domain.repository;

import com.xyz.roemahduren.domain.entity.Supplier;

import java.util.Optional;

public interface SupplierRepository extends CrudRepository<Supplier, String> {

    Optional<Supplier> findByMobilePhone(String mobilePhone);

}
