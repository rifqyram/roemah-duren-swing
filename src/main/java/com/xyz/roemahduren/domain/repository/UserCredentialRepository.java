package com.xyz.roemahduren.domain.repository;

import com.xyz.roemahduren.domain.entity.UserCredential;

import java.util.Optional;

public interface UserCredentialRepository extends CrudRepository<UserCredential, String> {

    Optional<UserCredential> findByEmail(String email);

}
