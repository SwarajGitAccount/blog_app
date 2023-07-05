package com.blog.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PostDto {
    private long id;
    @NotEmpty
    @Size(min=10,message = "title should consist of 10 letter's")
    private String title;
    @Size(min = 10,message = "Description must need of 10 letters")
    private String description;
    private String content;
}
