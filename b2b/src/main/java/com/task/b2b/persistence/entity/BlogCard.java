package com.task.b2b.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "blogDetails")
public class BlogCard {

    @Id
    private String id;
    private String blogHeader;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBlogHeader() {
        return blogHeader;
    }

    public void setBlogHeader(String blogHeader) {
        this.blogHeader = blogHeader;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
