package com.blog.service;

import com.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createCommentByPostId(long postId,CommentDto dto);
    List<CommentDto> getAllComments(long postId);
    CommentDto updateOneComment(long postId,long commentId,CommentDto dto);
    void deleteOneCommentByPostId(long postId,long commentId);
    List<CommentDto> getOneCommentByEmail(String email);
}

