package com.example.twitter_project.repository;

import com.example.twitter_project.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Query("SELECT c FROM Comment c WHERE c.user.id= :userId ")
    List<Comment> findCommentsByUserId(@Param("userId") Long userId);
}
