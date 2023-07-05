package com.blog.serviceImpl;

import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.payload.CommentDto;
import com.blog.repository.CommentRepository;
import com.blog.repository.PostRepository;
import com.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepo;
    @Autowired
    private PostRepository postRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createCommentByPostId(long postId, CommentDto dto) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("I'd not found :" + postId));
        Comment comment = this.mapToEntity(dto);
        comment.setPost(post);
        Comment savedComment = commentRepo.save(comment);
        return this.mapToDto(savedComment);

    }

    @Override
    public List<CommentDto> getAllComments(long postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("I'd not found with id:" + postId));
        List<Comment> allComments = commentRepo.findByPostId(postId);
        List<CommentDto> collect = allComments.stream().map((x) -> this.mapToDto(x)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public CommentDto updateOneComment(long postId, long commentId, CommentDto dto) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("I'd not found with :" + postId));
        Comment com = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("I'd not found with :" + commentId));
        com.setBody(dto.getBody());
        com.setName(dto.getName());
        com.setEmail(dto.getEmail());
        com.setPost(post);
        Comment updatedCom = commentRepo.save(com);
        return this.mapToDto(updatedCom);
    }

    @Override
    public void deleteOneCommentByPostId(long postId, long commentId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("I'd not found with :" + postId));
        Comment com = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("I'd not found with :" + commentId));
        com.setPost(post);
        commentRepo.delete(com);
    }

    @Override
    public List<CommentDto> getOneCommentByEmail(String email) {
        List<Comment> byEmail = commentRepo.findByEmail(email);
        List<CommentDto> collect = byEmail.stream().map((x) -> this.mapToDto(x)).collect(Collectors.toList());
        return collect;
    }

    private Comment mapToEntity(CommentDto dto){
        Comment comment=this.modelMapper.map(dto,Comment.class);
        return comment;
    }
    public CommentDto mapToDto(Comment com){
        CommentDto dto=this.modelMapper.map(com,CommentDto.class);
        return dto;
    }

}
