package com.blog.service;

import com.blog.payload.PostDto;

import java.util.List;

public interface PostService {

    PostDto createOnePost(PostDto dto);
    List<PostDto> listOfAllPosts();
    PostDto updateOnePost(long id,PostDto dto);
    void deleteOnePost(long id);
    PostDto getOnePostById(long id);
    List<PostDto> getOnePostByContent(String content);





}
