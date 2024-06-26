package com.rbs.blog.models;

public class ResponseDTO {
    private boolean authorized;
    public ResponseDTO() {
    }
    public ResponseDTO(boolean authorized) {
        this.authorized = authorized;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }
}
