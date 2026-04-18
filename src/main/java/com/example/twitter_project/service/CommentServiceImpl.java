package com.example.twitter_project.service;

import com.example.twitter_project.dto.request.CommentRequestDto;
import com.example.twitter_project.dto.request.CommentUpdateRequestDto;
import com.example.twitter_project.dto.response.CommentResponseDto;
import com.example.twitter_project.entity.Comment;
import com.example.twitter_project.entity.Tweet;
import com.example.twitter_project.entity.User;
import com.example.twitter_project.exception.TwitterException;
import com.example.twitter_project.repository.CommentRepository;
import com.example.twitter_project.repository.TweetRepository;
import com.example.twitter_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    private CommentRepository commentRepository;
    private TweetRepository tweetRepository;
    private UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, TweetRepository tweetRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<CommentResponseDto> findCommentsByUserId(Long userId) {

        User foundUser = userRepository.findById(userId).orElseThrow(
                ()-> new TwitterException("User not found with given id: " + userId, HttpStatus.NOT_FOUND)
        );

        List<Comment> commentList = foundUser.getUserComments();

        return commentList.stream()
                        .map(comment -> new CommentResponseDto(
                            foundUser.getEmail(),
                            comment.getContent(),
                            comment.getTweet().getId(),
                            "Yorumlar başarıyla getirilmiştir."))
                        .toList();
    }

    @Override
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto, String email) {

        Tweet tweet = tweetRepository.findById(commentRequestDto.tweetId()).orElseThrow(
                ()-> new TwitterException("Tweet not found with given id: " + commentRequestDto.tweetId(),
                        HttpStatus.NOT_FOUND)
        );

        User user = userRepository.findByEmail(email).orElseThrow(
                ()-> new TwitterException("User not found",HttpStatus.NOT_FOUND)
        );

        Comment comment = new Comment();
        comment.setContent(commentRequestDto.content());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(null);

        user.addComment(comment);
        tweet.addComment(comment);

        Comment savedComment = commentRepository.save(comment);
        return new CommentResponseDto(savedComment.getUser().getEmail(),
                savedComment.getContent(),
                savedComment.getTweet().getId(),
                "Yorumunuz başarıyla paylaşılmıştır.") ;
    }

    @Override
    public CommentResponseDto updateComment(Long commentId, CommentUpdateRequestDto commentUpdateRequestDto, String email) {

        Comment foundComment = commentRepository.findById(commentId).orElseThrow(
                ()-> new TwitterException("Comment not found with given id: " + commentId,HttpStatus.NOT_FOUND)
        );

        User foundUser = userRepository.findByEmail(email).orElseThrow(
                ()->new TwitterException("User not found",HttpStatus.NOT_FOUND)
        );

        if(!foundUser.getId().equals(foundComment.getUser().getId())){
            throw new TwitterException("You are not authorized to update this comment",
                    HttpStatus.FORBIDDEN);
        }

        foundComment.setContent(commentUpdateRequestDto.content());
        foundComment.setUpdatedAt(LocalDateTime.now());

        Comment updatedComment = commentRepository.save(foundComment);
        return new CommentResponseDto(
                updatedComment.getUser().getEmail(),
                updatedComment.getContent(),
                updatedComment.getTweet().getId(),
                "Yorumunuz başarıyla güncellenmiştir."
                );
    }

    @Override
    public void deleteComment(Long commentId, String email) {

        Comment foundComment = commentRepository.findById(commentId).orElseThrow(
                ()-> new TwitterException("Comment not found with given id: " + commentId,
                        HttpStatus.NOT_FOUND)
        );

        User foundUser = userRepository.findByEmail(email).orElseThrow(
                ()->new TwitterException("User not found",HttpStatus.NOT_FOUND)
        );

        Tweet relatedTweet = foundComment.getTweet();
        /// sahiplik kontrol ediliyor, yorum sahibi mi tweet sahibi mi bakıyoruz.
        if(relatedTweet.getUser().getId().equals(foundUser.getId())
                || foundComment.getUser().getId().equals(foundUser.getId())){
            commentRepository.delete(foundComment);
        }else{
            throw new TwitterException("You are not authorized to delete this comment!",HttpStatus.FORBIDDEN);
        }

    }
}
