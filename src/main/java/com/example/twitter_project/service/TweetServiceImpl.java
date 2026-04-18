package com.example.twitter_project.service;

import com.example.twitter_project.dto.request.TweetRequestDto;
import com.example.twitter_project.dto.request.TweetUpdateRequestDto;
import com.example.twitter_project.dto.response.TweetResponseDto;
import com.example.twitter_project.entity.Tweet;
import com.example.twitter_project.entity.User;
import com.example.twitter_project.exception.TwitterException;
import com.example.twitter_project.repository.TweetRepository;
import com.example.twitter_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TweetServiceImpl implements TweetService{


    private TweetRepository tweetRepository;
    private UserRepository userRepository;

    @Autowired
    public TweetServiceImpl(TweetRepository tweetRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TweetResponseDto createTweet(TweetRequestDto tweetRequestDto, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new TwitterException("User not found", HttpStatus.NOT_FOUND));

        Tweet tweet = new Tweet();
        tweet.setContent(tweetRequestDto.content());
        tweet.setCreatedAt(LocalDateTime.now());
        tweet.setUpdatedAt(null);
        //tweet.setUser(user);
        /// SETUSER(USER) İŞLEMİ ADDTWEET METODU İÇERİSİNDE GERÇEKLEŞTİRİYORUZ.
        ///TEK BİR YERDEN YÖNETİYORUZ
        user.addTweet(tweet);

        Tweet savedTweet = tweetRepository.save(tweet);

        return  new TweetResponseDto(
                user.getEmail(),
                savedTweet.getContent(),
                "Tweet başarıyla paylaşılmıştır."
        );
    }

    @Override
    public List<TweetResponseDto> findTweetsByUserId(Long userId) {

        User user = userRepository.findById(userId).
                orElseThrow(()-> new TwitterException("User not found!",HttpStatus.NOT_FOUND));

        List<Tweet> getirilenTweetler = tweetRepository.findTweetsByUserId(userId);

        return getirilenTweetler.stream()
                .map((tweet)-> new TweetResponseDto(
                        user.getEmail(),
                        tweet.getContent(),
                        "Tweet başarıyla getirilmiştir."
                )).toList();
    }


    @Override
    public TweetResponseDto findByTweetId(Long tweetId) {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(
                ()-> new TwitterException("Tweet not found with given id: " + tweetId,HttpStatus.NOT_FOUND)
        );

        return new TweetResponseDto(tweet.getUser().getEmail(),
                tweet.getContent(),
                "Tweet başarıyla getirildi.");
    }

    @Override
    public TweetResponseDto updateTweet(Long tweetId, TweetUpdateRequestDto tweetUpdateRequestDto, String email) {

        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(
                ()-> new TwitterException("Tweet not found with given id: " + tweetId, HttpStatus.NOT_FOUND)
        );
        User user = userRepository.findByEmail(email).orElseThrow(
                ()-> new TwitterException("User not found",HttpStatus.NOT_FOUND)
        );

        if(!tweet.getUser().getId().equals(user.getId())){
            throw new TwitterException("You are not authorized to update this tweet!",HttpStatus.FORBIDDEN);
        }

        tweet.setContent(tweetUpdateRequestDto.content());
        tweet.setUpdatedAt(LocalDateTime.now());

        Tweet updatedTweet = tweetRepository.save(tweet);

        return new TweetResponseDto(
                updatedTweet.getUser().getEmail(),
                updatedTweet.getContent(),
                "Tweet başarıyla güncellenmiştir");
    }

    @Override
    public void deleteTweet(Long tweetId, String email) {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(
                ()-> new TwitterException("Tweet not found with given id: "+tweetId,HttpStatus.NOT_FOUND)
        );

        User user = userRepository.findByEmail(email).orElseThrow(
                ()-> new TwitterException("User not found!",HttpStatus.NOT_FOUND)
        );

        if(!tweet.getUser().getId().equals(user.getId())){
            throw new TwitterException("You are not authorized to delete this tweet",HttpStatus.FORBIDDEN);
        }
        tweetRepository.delete(tweet);
    }
}
