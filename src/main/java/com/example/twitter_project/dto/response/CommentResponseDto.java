package com.example.twitter_project.dto.response;

public record CommentResponseDto(
        String email,
        String content,
        Long tweetId,
        String message
) {
}
