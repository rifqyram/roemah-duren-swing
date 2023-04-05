package com.xyz.roemahduren.domain.entity;

import com.xyz.roemahduren.domain.annotation.Column;
import com.xyz.roemahduren.domain.annotation.Id;
import com.xyz.roemahduren.domain.annotation.Table;

@Table(name = "m_customer")
public class Customer {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    @Column(name = "address")
    private String address;

    public Customer() {
    }

    public Customer(String id, String name, String mobilePhone, String address) {
        this.id = id;
        this.name = name;
        this.mobilePhone = mobilePhone;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
