package com.task.b2b.persistence.service.impl;

import com.task.b2b.persistence.entity.BlogCard;
import com.task.b2b.persistence.repo.BlogCardRepo;
import com.task.b2b.persistence.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import com.task.b2b.persistence.exception.BlogNotFoundException;
import com.task.b2b.persistence.exception.BlogServiceException;


import java.util.Optional;

@Component
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogCardRepo blogCardRepository;

    @Override
    public BlogCard saveOrUpdate(BlogCard blogCard) {
        try {
            return blogCardRepository.save(blogCard);
        } catch (Exception e) {
            throw new BlogServiceException("Error occurred while saving or updating the blog", e);
        }
    }

    @Override
    public Page<BlogCard> getAllBlogs(Pageable pageable) {
        try {
            return blogCardRepository.findAll(pageable);
        } catch (Exception e) {
            throw new BlogServiceException("Error occurred while retrieving all blogs", e);
        }
    }

    @Override
    public Optional<BlogCard> getBlogById(String id) {
        try {
            Optional<BlogCard> blog = blogCardRepository.findById(id);
            if (blog.isPresent()) {
                return blog;
            } else {
                throw new BlogNotFoundException("Blog with id " + id + " not found");
            }
        } catch (Exception e) {
            throw new BlogServiceException("Error occurred while retrieving the blog with id " + id, e);
        }
    }

    @Override
    public void deleteBlogById(String id) {
        try {
            Optional<BlogCard> blog = blogCardRepository.findById(id);
            if (blog.isPresent()) {
                blogCardRepository.deleteById(id);
            } else {
                throw new BlogNotFoundException("Cannot delete. Blog with id " + id + " not found");
            }
        } catch (Exception e) {
            throw new BlogServiceException("Error occurred while deleting the blog with id " + id, e);
        }
    }
}




