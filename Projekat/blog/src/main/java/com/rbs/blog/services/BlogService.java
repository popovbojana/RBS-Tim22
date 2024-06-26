package com.rbs.blog.services;

import com.rbs.blog.models.AclDTO;
import com.rbs.blog.models.BlogDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {

    private final WebClient.Builder webClientBuilder;
    private final ObjectMapper objectMapper;
    private final List<BlogDTO> blogs = new ArrayList<>();

    @Autowired
    public BlogService(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.webClientBuilder = webClientBuilder;
        this.objectMapper = objectMapper;
    }

    public void createBlog(BlogDTO blog, String user) {
        blogs.add(blog);
        sendAcl(user, "owner", "blog:" + blogs.size());
    }

    public void updateBlog(int id, BlogDTO blog) {
        if (id > 0 && id <= blogs.size()) {
            blogs.set(id - 1, blog);
        }
    }

    public BlogDTO getBlog(int id) {
        if (id > 0 && id <= blogs.size()) {
            return blogs.get(id - 1);
        }
        return null;
    }

    public void deleteBlog(int id) {
        if (id > 0 && id <= blogs.size()) {
            blogs.remove(id - 1);
        }
    }

    private void sendAcl(String user, String role, String document) {
        try {
            AclDTO acl = new AclDTO(user, role, document);
            String jsonPayload = objectMapper.writeValueAsString(acl);
            webClientBuilder.build()
                    .post()
                    .uri("http://localhost:8081/api/auth")
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .body(Mono.just(jsonPayload), String.class)
                    .retrieve()
                    .bodyToMono(String.class)
                    .subscribe();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
