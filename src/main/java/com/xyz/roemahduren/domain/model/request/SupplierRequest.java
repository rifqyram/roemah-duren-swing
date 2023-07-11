package com.xyz.roemahduren.domain.model.request;

import com.xyz.roemahduren.domain.annotation.validation.NotBlank;
import com.xyz.roemahduren.util.RandomGenerator;

public class SupplierRequest {
    private String id;

    @NotBlank
    private String name;
    @NotBlank
    private String address;

    @NotBlank
    private String mobilePhone;

    public SupplierRequest(String name, String address, String mobilePhone) {
        this.id = RandomGenerator.generateUUID();
        this.name = name;
        this.address = address;
        this.mobilePhone = mobilePhone;
    }

    public SupplierRequest(String id, String name, String address, String mobilePhone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.mobilePhone = mobilePhone;
    }

    public SupplierRequest() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
}
