package com.task.b2b.persistence.service.impl;

import com.task.b2b.persistence.entity.BlogCard;
import com.task.b2b.persistence.model.BlogCardView;
import com.task.b2b.persistence.repo.BlogCardRepo;
import com.task.b2b.persistence.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import com.task.b2b.persistence.exception.BlogNotFoundException;
import com.task.b2b.persistence.exception.BlogServiceException;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

//    @Override
//    public Page<BlogCard> getAllBlogs(Pageable pageable) {
//        try {
//            return blogCardRepository.findAll(pageable);
//        } catch (Exception e) {
//            throw new BlogServiceException("Error occurred while retrieving all blogs", e);
//        }
//    }

    @Override
    public Page<BlogCardView> getAllBlogs(Pageable pageable) {
        try {
            Page<BlogCard> blogs = blogCardRepository.findAll(pageable);
            List<BlogCardView> blogCardDtos = blogs.getContent().stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());

            return new PageImpl<>(blogCardDtos, pageable, blogs.getTotalElements());
        } catch (Exception e) {
            throw new BlogServiceException("Error occurred while retrieving all blogs", e);
        }
    }

    private BlogCardView convertToDto(BlogCard blog) {
        String shortDescription = blog.getDescription().length() > 100
                ? blog.getDescription().substring(0, 100) + "..."
                : blog.getDescription();

        BlogCardView blogCardView = new BlogCardView();

        blogCardView.setId(blog.getId());
        blogCardView.setTitle(blog.getBlogHeader());
        blogCardView.setShortDescription(shortDescription);
        blogCardView.setFullDescription(blog.getDescription());

        return blogCardView;
    }

    @Override
    public Optional<BlogCard> getBlogById(String id) {
        try {
            return blogCardRepository.findById(id);
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




