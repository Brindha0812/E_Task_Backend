package com.task.b2b.persistence.service;

import com.task.b2b.persistence.entity.BlogCard;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
public interface BlogService {

    BlogCard saveOrUpdate(BlogCard blogCard);

    List<BlogCard> getAllBlogs();

    Optional<BlogCard> getBlogById(String id);

    void deleteBlogById(String id);
}
