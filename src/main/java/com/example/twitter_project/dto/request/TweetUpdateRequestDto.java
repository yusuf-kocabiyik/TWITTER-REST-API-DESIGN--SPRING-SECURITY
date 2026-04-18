package com.example.twitter_project.dto.request;

import jakarta.validation.constraints.NotBlank;

public record TweetUpdateRequestDto(
        @NotBlank
        String content
) {
}
