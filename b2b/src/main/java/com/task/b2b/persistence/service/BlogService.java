package com.task.b2b.persistence.service;

import com.task.b2b.persistence.entity.BlogCard;
import com.task.b2b.persistence.model.BlogCardView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface BlogService {

    BlogCard saveOrUpdate(BlogCard blogCard);

    Page<BlogCardView> getAllBlogs(Pageable pageable);

    Optional<BlogCard> getBlogById(String id);

    void deleteBlogById(String id);
}
