package com.example.twitter_project.dto.response;

public record LikeResponseDto(
        String email,
        Long tweetId,
        String message
) {
}
