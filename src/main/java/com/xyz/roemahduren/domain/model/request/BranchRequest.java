package com.xyz.roemahduren.domain.model.request;

import com.xyz.roemahduren.domain.annotation.validation.NotBlank;
import com.xyz.roemahduren.util.RandomGenerator;

public class BranchRequest {

    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String address;

    public BranchRequest() {
    }

    public BranchRequest(String name, String address) {
        this.id = RandomGenerator.generateUUID();
        this.name = name;
        this.address = address;
    }

    public BranchRequest(String id, String name, String address) {
        this.id = id;
        this.name = name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
