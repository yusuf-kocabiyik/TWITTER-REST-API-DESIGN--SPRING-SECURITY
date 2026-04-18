package com.example.twitter_project.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDto(

        @NotBlank
        @Size(max=150)
        @Email
        String email,

        @NotBlank
        @Size(max=150)
        String password) {
}
