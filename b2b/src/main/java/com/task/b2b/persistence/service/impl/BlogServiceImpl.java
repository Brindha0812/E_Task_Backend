package com.task.b2b.persistence.service.impl;

import com.task.b2b.persistence.entity.BlogCard;
import com.task.b2b.persistence.repo.BlogCardRepo;
import com.task.b2b.persistence.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogCardRepo blogCardRepository;

    @Override
    public BlogCard saveOrUpdate(BlogCard blogCard) {
        return blogCardRepository.save(blogCard);
    }

    @Override
    public List<BlogCard> getAllBlogs() {
        return blogCardRepository.findAll();
    }

    @Override
    public Optional<BlogCard> getBlogById(String id) {
        return blogCardRepository.findById(id);
    }

    @Override
    public void deleteBlogById(String id) {
        blogCardRepository.deleteById(id);
    }
}




