package com.xyz.roemahduren.domain.entity;

import com.xyz.roemahduren.domain.annotation.db.Column;
import com.xyz.roemahduren.domain.annotation.db.Id;
import com.xyz.roemahduren.domain.annotation.db.Table;
import com.xyz.roemahduren.domain.annotation.validation.NotBlank;

@Table(name = "m_branch")
public class Branch {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    public Branch() {
    }

    public Branch(String id, String name, String address) {
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
