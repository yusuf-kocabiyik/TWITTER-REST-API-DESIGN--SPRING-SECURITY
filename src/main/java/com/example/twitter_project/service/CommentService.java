package com.example.twitter_project.service;

import com.example.twitter_project.dto.request.CommentRequestDto;
import com.example.twitter_project.dto.request.CommentUpdateRequestDto;
import com.example.twitter_project.dto.response.CommentResponseDto;

import java.util.List;

public interface CommentService {
    List<CommentResponseDto> findCommentsByUserId(Long userId);

    CommentResponseDto createComment(CommentRequestDto commentRequestDto,String email);

    CommentResponseDto updateComment(Long commentId, CommentUpdateRequestDto commentUpdateRequestDto, String email);

    void deleteComment(Long commentId, String email);
}
