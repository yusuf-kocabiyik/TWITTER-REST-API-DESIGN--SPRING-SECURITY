package com.example.twitter_project.controller;

import com.example.twitter_project.dto.request.RegisterRequestDto;
import com.example.twitter_project.dto.response.RegisterResponseDto;
import com.example.twitter_project.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponseDto register(@Validated @RequestBody RegisterRequestDto registerRequestDto){
        return authService.register(registerRequestDto);
    }
}

