package com.xyz.roemahduren.domain.model.request;

import com.xyz.roemahduren.util.RandomGenerator;

public class CustomerRequest {
    private String id;

    private String name;

    private String mobilePhone;

    private String address;

    public CustomerRequest(String name, String mobilePhone, String address) {
        this.id = RandomGenerator.generateUUID();
        this.name = name;
        this.mobilePhone = mobilePhone;
        this.address = address;
    }

    public CustomerRequest(String id, String name, String mobilePhone, String address) {
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
