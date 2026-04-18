package com.example.twitter_project.entity;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;

@Table(name = "likes",schema = "twitter")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "tweet_id",nullable = false)
    private Tweet tweet;
}
