package com.task.b2b.persistence.model;

import com.task.b2b.persistence.entity.BlogCard;

public class BlogResponse {

    private String message;
    private BlogCard blogCard;

    public BlogResponse(String message, BlogCard blogCard) {
        this.message = message;
        this.blogCard = blogCard;
    }

    public String getMessage() {
        return message;
    }

    public BlogCard getBlogCard() {
        return blogCard;
    }

}
