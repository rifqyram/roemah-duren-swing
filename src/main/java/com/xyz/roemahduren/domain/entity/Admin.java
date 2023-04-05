package com.xyz.roemahduren.domain.entity;

import com.xyz.roemahduren.domain.annotation.Column;
import com.xyz.roemahduren.domain.annotation.Id;
import com.xyz.roemahduren.domain.annotation.Table;

@Table(name = "m_user_admin")
public class Admin {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    @Column(name = "user_credential_id")
    private String userCredentialId;

    public Admin() {
    }

    public Admin(String id, String name, String mobilePhone, String userCredentialId) {
        this.id = id;
        this.name = name;
        this.mobilePhone = mobilePhone;
        this.userCredentialId = userCredentialId;
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

    public String getUserCredentialId() {
        return userCredentialId;
    }

    public void setUserCredentialId(String userCredentialId) {
        this.userCredentialId = userCredentialId;
    }
}
