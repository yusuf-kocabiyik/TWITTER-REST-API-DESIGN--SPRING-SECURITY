package com.example.twitter_project.controller;

import com.example.twitter_project.dto.request.CommentRequestDto;
import com.example.twitter_project.dto.request.CommentUpdateRequestDto;
import com.example.twitter_project.dto.response.CommentResponseDto;
import com.example.twitter_project.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentResponseDto> findCommentsByUserId(@PathVariable Long userId){
        return commentService.findCommentsByUserId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponseDto createComment(@Validated @RequestBody CommentRequestDto commentRequestDto,
                                            Authentication authentication){
        return commentService.createComment(commentRequestDto,authentication.getName());
    }

    @PutMapping("/{commentId}")
    public CommentResponseDto updateComment(@Validated @RequestBody CommentUpdateRequestDto commentUpdateRequestDto,
                                            @PathVariable Long commentId,
                                            Authentication authentication){
        return commentService.updateComment(commentId,commentUpdateRequestDto, authentication.getName());
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteComment(@PathVariable Long commentId,Authentication authentication){
        commentService.deleteComment(commentId,authentication.getName());
        return "Yorum başarıyla silinmiştir.";
    }
}
