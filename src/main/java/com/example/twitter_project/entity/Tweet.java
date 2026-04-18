package com.example.twitter_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import lombok.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name="tweets", schema="twitter")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")

public class Tweet {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

    @NotBlank
    private String content;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "tweet")
    private List<Comment> tweetComments = new ArrayList<>();

    public void addComment(Comment comment){
        this.tweetComments.add(comment);
        comment.setTweet(this);
    }

    public void removeComment(Comment comment){
        this.tweetComments.remove(comment);
    }

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "tweet")
    private List<Like> tweetLikes = new ArrayList<>();

    public void addLike(Like like){
        this.tweetLikes.add(like);
        like.setTweet(this);
    }

    public void removeLike(Like like){
        this.tweetLikes.remove(like);
        like.setTweet(null);
    }

}
