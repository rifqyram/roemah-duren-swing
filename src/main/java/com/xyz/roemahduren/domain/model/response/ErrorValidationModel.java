package com.xyz.roemahduren.domain.model.response;

import java.util.Set;

public class ErrorValidationModel {

    private String path;
    private Set<String> messages;

    public ErrorValidationModel(String path, Set<String> messages) {
        this.path = path;
        this.messages = messages;
    }

    public String getPath() {
        return path;
    }

    public Set<String> getMessages() {
        return messages;
    }

}
