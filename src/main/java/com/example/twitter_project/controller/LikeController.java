package com.example.twitter_project.controller;

import com.example.twitter_project.dto.request.LikeRequestDto;
import com.example.twitter_project.dto.response.LikeResponseDto;
import com.example.twitter_project.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class LikeController {
    private LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/like")
    public LikeResponseDto likeTweet(@Validated @RequestBody LikeRequestDto likeRequestDto,
                                     Authentication authentication){
        return likeService.likeTweet(likeRequestDto.tweetId(),authentication.getName());
    }

    @PostMapping("/dislike")
    public LikeResponseDto dislikeTweet(@Validated @RequestBody LikeRequestDto likeRequestDto,
                                        Authentication authentication){
        return likeService.dislikeTweet(likeRequestDto.tweetId(), authentication.getName());
    }

}
