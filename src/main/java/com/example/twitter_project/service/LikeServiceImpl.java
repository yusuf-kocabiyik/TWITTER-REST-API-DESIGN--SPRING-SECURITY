package com.example.twitter_project.service;

import com.example.twitter_project.dto.response.LikeResponseDto;
import com.example.twitter_project.entity.Like;
import com.example.twitter_project.entity.Tweet;
import com.example.twitter_project.entity.User;
import com.example.twitter_project.exception.TwitterException;
import com.example.twitter_project.repository.LikeRepository;
import com.example.twitter_project.repository.TweetRepository;
import com.example.twitter_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService{

    private LikeRepository likeRepository;
    private TweetRepository tweetRepository;
    private UserRepository userRepository;

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository, TweetRepository tweetRepository, UserRepository userRepository) {
        this.likeRepository = likeRepository;
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    @Override
    public LikeResponseDto likeTweet(Long tweetId, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                ()-> new TwitterException("User not found", HttpStatus.NOT_FOUND)
        );

        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(
                ()-> new TwitterException("Tweet not found with given id: " + tweetId,HttpStatus.NOT_FOUND)
        );

        Optional<Like> foundLike = likeRepository.findLikeByUserIdAndTweetId(user.getId(),tweet.getId());

        if(foundLike.isPresent()){
            throw new TwitterException("You already liked this tweet",HttpStatus.BAD_REQUEST);
        }

        Like like = new Like();
        like.setCreatedAt(LocalDateTime.now());

        user.addLike(like);
        tweet.addLike(like);

        Like savedLike = likeRepository.save(like);

        return new LikeResponseDto(
                savedLike.getUser().getEmail(),
                savedLike.getTweet().getId(),
                "Tweet'e başarıyla like atılmıştır."
        );
    }

    @Override
    public LikeResponseDto dislikeTweet(Long tweetId, String email) {

        User user = userRepository.findByEmail(email).orElseThrow(
                ()-> new TwitterException("User not found",HttpStatus.NOT_FOUND)
        );

        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(
                ()-> new TwitterException("Tweet not found with given id: " + tweetId,HttpStatus.NOT_FOUND)
        );

        Like foundLike = likeRepository.findLikeByUserIdAndTweetId(user.getId(), tweet.getId())
                .orElseThrow(()-> new TwitterException("Like not found for this user and tweet!",HttpStatus.NOT_FOUND));

        likeRepository.delete(foundLike);

        return new LikeResponseDto(
                foundLike.getUser().getEmail(),
                foundLike.getTweet().getId(),
                "Tweet beğenisi başarıyla kaldırılmıştır."
        );

    }
}
