package com.task.b2b.persistence.controller;

import com.task.b2b.persistence.entity.BlogCard;
import com.task.b2b.persistence.service.BlogService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

@CrossOrigin
@RestController
@RequestMapping("/b2b")
public class BlogController {

    @Autowired
    BlogService blogService;

    // Create a new blog
    @PostMapping
    public ResponseEntity<BlogCard> createBlog(@RequestBody BlogCard blogCard) {
        BlogCard savedBlog = blogService.saveOrUpdate(blogCard);
        return ResponseEntity.ok(savedBlog);
    }

    // Get all blogs
    @GetMapping("/all")
    public ResponseEntity<Page<BlogCard>> getAllBlogs(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<BlogCard> blogs = blogService.getAllBlogs(pageable);
        return ResponseEntity.ok(blogs);
    }

    @GetMapping("/all/sort")
    public ResponseEntity<Page<BlogCard>> getAllBlogs(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size,
                                                      @RequestParam(defaultValue = "id") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<BlogCard> blogs = blogService.getAllBlogs(pageable);
        return ResponseEntity.ok(blogs);
    }

    // Get a blog by ID
    //    @GetMapping("/{id}")
    //    public ResponseEntity<BlogCard> getBlogById(@PathVariable String id) {
    //        Optional<BlogCard> blogCard = blogService.getBlogById(String.valueOf(id));
    //        return blogCard.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    //    }

    // Update a blog
    @PostMapping("update/{id}")
    public ResponseEntity<BlogCard> updateBlog(@PathVariable String id,
                                               @RequestBody BlogCard blogCard) {

        if (blogService.getBlogById(String.valueOf(id)).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        blogCard.setId(String.valueOf(id));
        BlogCard updatedBlog = blogService.saveOrUpdate(blogCard);
        return ResponseEntity.ok(updatedBlog);
    }

    // Delete a blog by ID
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteBlogById(@PathVariable String id) {

        if (blogService.getBlogById(String.valueOf(id)).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        blogService.deleteBlogById(String.valueOf(id));
        return ResponseEntity.noContent().build();
    }
}
