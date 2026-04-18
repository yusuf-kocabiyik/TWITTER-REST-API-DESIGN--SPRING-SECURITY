package com.example.twitter_project.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CommentUpdateRequestDto (
        @NotBlank
        String content
){
}
