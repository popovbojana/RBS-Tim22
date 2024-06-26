package com.rbs.blog.services;

import com.rbs.blog.models.AclDTO;
import com.rbs.blog.models.BlogDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbs.blog.models.ResponseDTO;
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

    public boolean createBlog(BlogDTO blog) {
        blogs.add(blog);
        return sendAcl(blog.getUser(), "owner", "doc:" +blog.getTitle());
    }

    public boolean updateBlog(BlogDTO blog) {

     for(BlogDTO b : blogs) {
         if (b.getTitle().equals(blog.getTitle()) && b.getUser().equals(blog.getUser())) {
             boolean response = check(blog.getUser(), "editor", "doc:" + blog.getTitle());
             if(response){
                 b.setText(blog.getText());
                 return true;
             }
         }
     }
     return false;
    }
    //TODO: nisu bitne toliko za rad zanzibara
    public BlogDTO getBlog(int id) {
        return null;
    }

    public void deleteBlog(int id) {
    }

    private boolean sendAcl(String object, String relation, String user) {
        try {
            AclDTO acl = new AclDTO(user, relation, object);
            String jsonPayload = objectMapper.writeValueAsString(acl);
            webClientBuilder.build()
                    .post()
                    .uri("http://localhost:8080/api/auth/acl")
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .body(Mono.just(jsonPayload), String.class)
                    .retrieve()
                    .bodyToMono(String.class)
                    .subscribe();
            return true;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean check(String user, String relation, String object) {
        String uri = String.format("?object=%s:doc&relation=%s&user=%s", object, relation, user);
        ResponseDTO response = webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/api/auth/acl/check" + uri)
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .retrieve()
                .bodyToMono(ResponseDTO.class).block();
        return Boolean.parseBoolean(response.getAuthorized());
    }
}
