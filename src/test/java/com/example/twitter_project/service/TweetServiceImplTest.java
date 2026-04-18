package com.example.twitter_project.service;

import com.example.twitter_project.dto.request.TweetRequestDto;
import com.example.twitter_project.dto.request.TweetUpdateRequestDto;
import com.example.twitter_project.dto.response.TweetResponseDto;
import com.example.twitter_project.entity.Tweet;
import com.example.twitter_project.entity.User;
import com.example.twitter_project.exception.TwitterException;
import com.example.twitter_project.repository.TweetRepository;
import com.example.twitter_project.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TweetServiceImplTest {

    @Mock
    private TweetRepository tweetRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TweetServiceImpl tweetService;

    @Test
    void createTweet_success() {
        // given
        String email = "test@test.com";
        TweetRequestDto requestDto = new TweetRequestDto("test tweeti");

        User user = new User();
        user.setId(1L);
        user.setEmail(email);

        Tweet savedTweet = new Tweet();
        savedTweet.setId(1L);
        savedTweet.setContent("test tweeti");
        savedTweet.setUser(user);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(tweetRepository.save(any(Tweet.class))).thenReturn(savedTweet);

        // when
        TweetResponseDto response = tweetService.createTweet(requestDto, email);

        // then
        assertNotNull(response);
        //assertEquals("test@test.com", response.email());
        // assertEquals("İlk tweet", response.content());
        // assertEquals("Tweet başarıyla paylaşılmıştır.", response.message());

        //verify(userRepository).findByEmail(email);
        //verify(tweetRepository).save(any(Tweet.class));
    }

}
