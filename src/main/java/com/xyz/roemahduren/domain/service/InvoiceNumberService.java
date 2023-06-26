package com.xyz.roemahduren.domain.service;

import com.xyz.roemahduren.domain.entity.InvoiceNumber;

import java.sql.Date;

public interface InvoiceNumberService {
    String generateInvoiceNumber(InvoiceNumber invoiceNumber);

    InvoiceNumber getByDate(Date date);

}
