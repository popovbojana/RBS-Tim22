package com.rbs.blog.models;

public class ResponseDTO {
    private String authorized;

    public ResponseDTO(String authorized) {
        this.authorized = authorized;
    }

    public String getAuthorized() {
        return authorized;
    }

    public void setAuthorized(String authorized) {
        this.authorized = authorized;
    }
}
