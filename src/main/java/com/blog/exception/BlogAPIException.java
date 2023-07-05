package com.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice
public class BlogAPIException extends RuntimeException {

    public BlogAPIException() {
        super();
    }


    public BlogAPIException(HttpStatus httpStatus, String msg) {

    }
}
