package com.blog.serviceImpl;

import com.blog.entity.Post;
import com.blog.payload.PostDto;
import com.blog.repository.PostRepository;
import com.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createOnePost(PostDto dto) {
        Post post = this.mapToEntity(dto);
        Post savedPost = postRepo.save(post);
        return this.mapToDto(savedPost);
    }

    @Override
    public List<PostDto> listOfAllPosts() {
        List<Post> allPost = postRepo.findAll();
        List<PostDto> collect = allPost.stream().map((x) -> this.mapToDto(x)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public PostDto updateOnePost(long id, PostDto dto) {
        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("I'd not found :" + id));
       post.setContent(dto.getContent());
       post.setDescription(dto.getDescription());
       post.setTitle(dto.getTitle());
       Post updatedPost = postRepo.save(post);
        return this.mapToDto(updatedPost);


    }

    @Override
    public void deleteOnePost(long id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("I'd not found :" + id));
        postRepo.delete(post);
    }

    @Override
    public PostDto getOnePostById(long id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("I'd not found !" + id));
        return this.mapToDto(post);
    }

    @Override
    public List<PostDto> getOnePostByContent(String content) {
        List<Post> byContent = postRepo.findByContent(content);
        List<PostDto> collect = byContent.stream().map((x) -> this.mapToDto(x)).collect(Collectors.toList());
        return collect;
    }

    private Post mapToEntity(PostDto dto){
        Post post=this.modelMapper.map(dto,Post.class);
        return post;
    }
    public PostDto mapToDto(Post post){
        PostDto dto=this.modelMapper.map(post,PostDto.class);
        return dto;
    }



}
