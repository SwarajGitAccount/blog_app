package com.blog.controller;
import com.blog.payload.CommentDto;
import com.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController { //child table spring validation i think it is not going actually proper through
    @Autowired
    private CommentService commentService;
    //http://localhost:8080/api/post/{postId}/comments
    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<?> createOneCommentByPostId(@Valid @PathVariable long postId, @RequestBody CommentDto dto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        CommentDto dto1 = commentService.createCommentByPostId(postId, dto);
        return new ResponseEntity<>(dto1, HttpStatus.CREATED);
    }
    //http://localhost:8080/api/posts/get/5/comments
    @GetMapping("/posts/get/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getAllCommentsByPostId(@PathVariable long postId){
        List<CommentDto> dtos = commentService.getAllComments(postId);
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }
    //http://localhost:8080/api/posts/3/5/comments
    @PutMapping("/posts/{postId}/{commentId}/comments")
    public ResponseEntity<CommentDto> updateOneComment(@PathVariable long postId,@PathVariable long commentId,@RequestBody CommentDto dto){
        CommentDto dto1=commentService.updateOneComment(postId,commentId,dto);
        return new ResponseEntity<>(dto1,HttpStatus.OK);
    }
    //http://localhost:8080/api/post/postId/commentId/comments
    @DeleteMapping("/post/{postId}/{commentId}/comments")
    public ResponseEntity<String> deleteOneComment(@PathVariable long postId,@PathVariable long commentId){
        commentService.deleteOneCommentByPostId(postId,commentId);
        return new ResponseEntity<>("I'd has been deleted",HttpStatus.OK);
    }
    //http://localhost:8080/api/post/email/comments
    @GetMapping("/post/{email}/comments")
    public ResponseEntity<List<CommentDto>> getOneCommentByEmail(@PathVariable String email){
        List<CommentDto> byEmail = commentService.getOneCommentByEmail(email);
        return new ResponseEntity<>(byEmail,HttpStatus.OK);

    }
}
