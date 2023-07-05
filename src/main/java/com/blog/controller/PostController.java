package com.blog.controller;

import com.blog.payload.PostDto;
import com.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    //http://localhost:8080/api/posts/listAll
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<?> createOnePost(@Valid @RequestBody PostDto dto, BindingResult bindingResult){
       if(bindingResult.hasErrors()){
           return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
       }

        PostDto dto1=postService.createOnePost(dto);
        return new ResponseEntity<>(dto1, HttpStatus.CREATED);
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<PostDto>> getAllPosts(){
        List<PostDto> dtos = postService.listOfAllPosts();
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updateOnePost(@PathVariable long id,@RequestBody PostDto dto){
        PostDto dto1=postService.updateOnePost(id,dto);
        return new ResponseEntity<>(dto1,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOnePost(@PathVariable long id){
        postService.deleteOnePost(id);
        return new ResponseEntity<>("I'd hs been deleted successfully !",HttpStatus.OK);
    }
    //http://localhost:8080/api/posts/one/{id}
    @GetMapping("/one/{id}")
    public ResponseEntity<PostDto> getOnePostById(@PathVariable long id){
        PostDto dto=postService.getOnePostById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @GetMapping("/{content}")
    public ResponseEntity<List<PostDto>> getOnePostByContent(@PathVariable String content){
        List<PostDto> dto = postService.getOnePostByContent(content);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

}
