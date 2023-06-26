package com.xyz.roemahduren.domain.entity;

import com.xyz.roemahduren.domain.annotation.db.Column;
import com.xyz.roemahduren.domain.annotation.db.Id;
import com.xyz.roemahduren.domain.annotation.db.Table;

import java.sql.Date;

@Table(name = "m_invoice_number")
public class InvoiceNumber {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "invoice_date")
    private Date invoiceDate;
    @Column(name = "invoice_sequence")
    private Long invoiceSequence;

    public InvoiceNumber(String id, Date invoiceDate, Long invoiceSequence) {
        this.id = id;
        this.invoiceDate = invoiceDate;
        this.invoiceSequence = invoiceSequence;
    }

    public InvoiceNumber() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Long getInvoiceSequence() {
        return invoiceSequence;
    }

    public void setInvoiceSequence(Long invoiceSequence) {
        this.invoiceSequence = invoiceSequence;
    }
}
