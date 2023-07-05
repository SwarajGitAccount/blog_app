package com.blog.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CommentDto {
    private long id;
    @NotBlank
    @Size(min = 5,max = 20,message = "name should consist of 20 letters !!")
    private String name;
    @Email(message = "Email should be valid type !!")
    private String email;
    @NotEmpty
    private String body;
}
