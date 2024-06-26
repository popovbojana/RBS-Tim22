package com.rbs.blog.controllers;

import com.rbs.blog.models.BlogDTO;
import com.rbs.blog.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping
    public ResponseEntity<?> createBlog(@RequestBody BlogDTO blog) {
        if (blogService.createBlog(blog)){
            return new ResponseEntity<>("Successfully created a new blog.", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Error chile creating new blog.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateBlog(@RequestBody BlogDTO blog) {
        if (blogService.updateBlog(blog)){
            return new ResponseEntity<>("Successfully updated the blog.", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Error chile updating the blog.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public BlogDTO getBlog(@PathVariable int id) {
        return blogService.getBlog(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBlog(@PathVariable int id) {
        blogService.deleteBlog(id);
    }
}
