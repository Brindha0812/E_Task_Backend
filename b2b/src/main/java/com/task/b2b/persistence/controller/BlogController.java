package com.task.b2b.persistence.controller;

import com.task.b2b.persistence.entity.BlogCard;
import com.task.b2b.persistence.model.BlogResponse;
import com.task.b2b.persistence.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/b2b")
public class BlogController {

    @Autowired
    BlogService blogService;

    // Create a new blog
    @PostMapping
    public ResponseEntity<BlogResponse> createBlog(@Valid @RequestBody BlogCard blogCard) {
        BlogCard savedBlog = blogService.saveOrUpdate(blogCard);

        BlogResponse response = new BlogResponse("Blog created successfully.", savedBlog);
        return ResponseEntity.ok(response);
    }

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
    public ResponseEntity<String> deleteBlogById(@PathVariable String id) {

        if (blogService.getBlogById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Blog with id " + id + " not found.");
        }
        blogService.deleteBlogById(String.valueOf(id));
        return ResponseEntity.ok("Blog deleted successfully.");
    }

    // Get all blogs
    @GetMapping("/all")
    public ResponseEntity<Page<BlogCard>> getAllBlogs(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<BlogCard> blogs = blogService.getAllBlogs(pageable);
        return ResponseEntity.ok(blogs);
    }

    // Get all blogs with sorting
    @GetMapping("/all/sort")
    public ResponseEntity<Page<BlogCard>> getAllBlogs(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size,
                                                      @RequestParam(defaultValue = "id") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<BlogCard> blogs = blogService.getAllBlogs(pageable);
        return ResponseEntity.ok(blogs);
    }

}
