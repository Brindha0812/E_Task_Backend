package com.task.b2b.persistence.repo;

import com.task.b2b.persistence.entity.BlogCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogCardRepo extends MongoRepository<BlogCard, String> {
}
