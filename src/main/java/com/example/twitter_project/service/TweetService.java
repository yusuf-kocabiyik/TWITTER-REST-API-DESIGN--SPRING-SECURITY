package com.example.twitter_project.service;

import com.example.twitter_project.dto.request.TweetRequestDto;
import com.example.twitter_project.dto.request.TweetUpdateRequestDto;
import com.example.twitter_project.dto.response.TweetResponseDto;

import java.util.List;

public interface TweetService {

    TweetResponseDto createTweet(TweetRequestDto tweetRequestDto,String email);

    List<TweetResponseDto> findTweetsByUserId(Long userId);

    TweetResponseDto findByTweetId(Long tweetId);

    TweetResponseDto updateTweet(Long tweetId, TweetUpdateRequestDto tweetUpdateRequestDto,String email);

    void deleteTweet(Long tweetId, String email);
}
