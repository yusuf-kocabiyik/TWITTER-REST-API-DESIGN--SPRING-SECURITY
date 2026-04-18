package com.example.twitter_project.dto.response;

import java.time.LocalDateTime;

public record TweetResponseDto(
        String email,
        String content,
        String message
) {
}
