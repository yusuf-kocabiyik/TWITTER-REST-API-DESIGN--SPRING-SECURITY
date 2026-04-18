package com.example.twitter_project.service;

import com.example.twitter_project.dto.request.RegisterRequestDto;
import com.example.twitter_project.dto.response.RegisterResponseDto;

public interface AuthService {

    RegisterResponseDto register(RegisterRequestDto registerRequestDto);
}
