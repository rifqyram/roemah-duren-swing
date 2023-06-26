package com.xyz.roemahduren.application.service;

import com.xyz.roemahduren.domain.entity.InvoiceNumber;
import com.xyz.roemahduren.domain.repository.InvoiceNumberRepository;
import com.xyz.roemahduren.domain.service.InvoiceNumberService;
import com.xyz.roemahduren.util.RandomGenerator;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class InvoiceNumberServiceImpl implements InvoiceNumberService {

    private final InvoiceNumberRepository invoiceNumberRepository;

    public InvoiceNumberServiceImpl(InvoiceNumberRepository invoiceNumberRepository) {
        this.invoiceNumberRepository = invoiceNumberRepository;
    }

    @Override
    public String generateInvoiceNumber(InvoiceNumber request) {
        if (request == null) {
            InvoiceNumber invoiceNumber = new InvoiceNumber();
            invoiceNumber.setId(RandomGenerator.generateUUID());
            invoiceNumber.setInvoiceDate(Date.valueOf(LocalDate.now()));
            invoiceNumber.setInvoiceSequence(1L);
            invoiceNumberRepository.save(invoiceNumber);
            return "INV-" + getCurrentDate() + "-" + "000" + invoiceNumber.getInvoiceSequence();
        }

        request.setInvoiceSequence(request.getInvoiceSequence() + 1L);
        invoiceNumberRepository.update(request);

        if (request.getInvoiceSequence() < 10 && request.getInvoiceSequence() > 0) {
            return "INV-" + getCurrentDate() + "-" + "000" + request.getInvoiceSequence();
        } else if (request.getInvoiceSequence() < 100) {
            return "INV-" + getCurrentDate() + "-" + "00" + request.getInvoiceSequence();
        } else if (request.getInvoiceSequence() < 1000) {
            return "INV-" + getCurrentDate() + "-" + "0" + request.getInvoiceSequence();
        } else {
            return "INV-" + getCurrentDate() + "-" + request.getInvoiceSequence();
        }
    }

    @Override
    public InvoiceNumber getByDate(Date date) {
        return invoiceNumberRepository.findByInvoiceDate(date).orElse(null);
    }

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        java.util.Date date = new java.util.Date();
        return dateFormat.format(date);
    }

}
