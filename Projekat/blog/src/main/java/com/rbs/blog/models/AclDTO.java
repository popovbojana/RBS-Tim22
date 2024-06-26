package com.rbs.blog.models;

public class AclDTO {
    private String user;
    private String role;
    private String document;

    public AclDTO(String user, String role, String document) {
        this.user = user;
        this.role = role;
        this.document = document;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}
