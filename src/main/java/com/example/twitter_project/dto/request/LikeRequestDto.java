package com.example.twitter_project.dto.request;

import jakarta.validation.constraints.NotNull;

public record LikeRequestDto(
        @NotNull
        Long tweetId
) {
}
