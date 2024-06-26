package com.rbs.blog.controllers;

import com.rbs.blog.models.BlogDTO;
import com.rbs.blog.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void createBlog(@RequestBody BlogDTO blog, @RequestHeader("user") String user) {
        blogService.createBlog(blog, user);
    }

    @PutMapping("/{id}")
    public void updateBlog(@PathVariable int id, @RequestBody BlogDTO blog) {
        blogService.updateBlog(id, blog);
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
