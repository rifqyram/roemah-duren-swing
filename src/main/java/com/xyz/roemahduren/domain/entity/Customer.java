package com.xyz.roemahduren.domain.entity;

import com.xyz.roemahduren.domain.annotation.db.Column;
import com.xyz.roemahduren.domain.annotation.db.Id;
import com.xyz.roemahduren.domain.annotation.db.Table;

@Table(name = "m_customer")
public class Customer {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    public Customer() {
    }

    public Customer(String id, String name, String mobilePhone) {
        this.id = id;
        this.name = name;
        this.mobilePhone = mobilePhone;
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

}
