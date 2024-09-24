package com.task.b2b.persistence.exception;

public class BlogServiceException extends RuntimeException {
    public BlogServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
