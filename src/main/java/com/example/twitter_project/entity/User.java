package com.example.twitter_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Table(name="users",schema="twitter")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max=150)
    @Email
    private String email;

    @NotNull
    @NotBlank
    @Size(max=150)
    @ToString.Exclude
    private String password;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            schema = "twitter",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();


    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Tweet> tweets = new ArrayList<>();

    public void addTweet(Tweet tweet){
        this.tweets.add(tweet);
        tweet.setUser(this);
    }

    public void removeTweet(Tweet tweet){
        this.tweets.remove(tweet);
    }

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Comment> userComments = new ArrayList<>();

    public void addComment(Comment comment){
        this.userComments.add(comment);
        comment.setUser(this);
    }

    public void removeComment(Comment comment){
        this.userComments.remove(comment);
    }

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Like> userLikes = new ArrayList<>();

    public void addLike(Like like){
        this.userLikes.add(like);
        like.setUser(this);
    }

    public void removeLike(Like like){
        this.userLikes.remove(like);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
