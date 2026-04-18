package com.example.twitter_project.dto.request;

import jakarta.validation.constraints.NotBlank;

public record TweetRequestDto(
        @NotBlank
        String content
) {
}
