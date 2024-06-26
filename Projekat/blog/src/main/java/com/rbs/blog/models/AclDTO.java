package com.rbs.blog.models;

public class AclDTO {
    private String object;
    private String relation;
    private String user;

    public AclDTO(String object, String relation, String user) {
        this.object = object;
        this.relation = relation;
        this.user = user;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
