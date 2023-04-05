package com.xyz.roemahduren.domain.entity;

import com.xyz.roemahduren.domain.annotation.Column;
import com.xyz.roemahduren.domain.annotation.Id;
import com.xyz.roemahduren.domain.annotation.Table;

@Table(name = "m_user_credential")
public class UserCredential {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "last_active")
    private Long lastActive;

    public UserCredential() {
    }

    public UserCredential(String id, String email, String password, Long lastActive) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.lastActive = lastActive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getLastActive() {
        return lastActive;
    }

    public void setLastActive(Long lastActive) {
        this.lastActive = lastActive;
    }
}
