package com.xyz.roemahduren.domain.repository;

import com.xyz.roemahduren.domain.entity.InvoiceNumber;

import java.sql.Date;
import java.util.Optional;

public interface InvoiceNumberRepository extends CrudRepository<InvoiceNumber, String> {

    Optional<InvoiceNumber> findByInvoiceDate(Date date);

}
