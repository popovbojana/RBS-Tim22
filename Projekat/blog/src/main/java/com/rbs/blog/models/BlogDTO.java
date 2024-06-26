package com.rbs.blog.models;

public class BlogDTO {
    private String title;
    private String text;
    private String user;
    public BlogDTO(String title, String text, String user) {
        this.title = title;
        this.text = text;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}

