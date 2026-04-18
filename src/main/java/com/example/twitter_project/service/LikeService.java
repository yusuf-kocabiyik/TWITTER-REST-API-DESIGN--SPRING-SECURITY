package com.example.twitter_project.service;

import com.example.twitter_project.dto.response.LikeResponseDto;

public interface LikeService {
    LikeResponseDto likeTweet(Long tweetId, String email);

    LikeResponseDto dislikeTweet(Long tweetId, String email);
}
