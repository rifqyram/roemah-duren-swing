package com.xyz.roemahduren.exception;

import com.xyz.roemahduren.domain.model.response.ErrorValidationModel;

import java.util.Set;

public class ValidationException extends RuntimeException{

    private final Set<ErrorValidationModel> validationModels;

    public ValidationException(Set<ErrorValidationModel> validationModels) {
        this.validationModels = validationModels;
    }

    public Set<ErrorValidationModel> getValidationModels() {
        return validationModels;
    }
}
