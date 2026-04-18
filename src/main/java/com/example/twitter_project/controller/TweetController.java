package com.example.twitter_project.controller;

import com.example.twitter_project.dto.request.TweetRequestDto;
import com.example.twitter_project.dto.request.TweetUpdateRequestDto;
import com.example.twitter_project.dto.response.TweetResponseDto;
import com.example.twitter_project.entity.Tweet;
import com.example.twitter_project.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweet")
public class TweetController {

    private TweetService tweetService;

    @Autowired
    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TweetResponseDto createTweet(@Validated @RequestBody TweetRequestDto tweetRequestDto,
                                        Authentication authentication){
        return tweetService.createTweet(tweetRequestDto,authentication.getName());
    }

    //@CrossOrigin(origins = "http://localhost:3200")
    @GetMapping("/findByUserId/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TweetResponseDto> findTweetsByUserId(@PathVariable("userId") Long userId){
        return tweetService.findTweetsByUserId(userId);
    }

    /// Burada PathVariable yerine RequestParam kullanmak istedim.
    @GetMapping("/findById")
    @ResponseStatus(HttpStatus.OK)
    public TweetResponseDto findByTweetId(@RequestParam Long tweetId){
        return tweetService.findByTweetId(tweetId);
    }

    @PutMapping("/update/{tweetId}")
    public TweetResponseDto updateTweet(@PathVariable Long tweetId,
                                        @Validated @RequestBody TweetUpdateRequestDto tweetUpdateRequestDto,
                                        Authentication authentication){
        return tweetService.updateTweet(tweetId,tweetUpdateRequestDto, authentication.getName());
    }

    @DeleteMapping("/{tweetId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteTweet(@PathVariable Long tweetId,Authentication authentication){
        tweetService.deleteTweet(tweetId, authentication.getName());
        return "Tweet başarıyla silinmiştir";
    }

}
