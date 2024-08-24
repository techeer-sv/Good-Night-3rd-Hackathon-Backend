package com.example.TecheerTreeBackend.repository;

import com.example.TecheerTreeBackend.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT * FROM comment WHERE wish_id =:wishId AND is_deleted = FALSE", nativeQuery = true)
    List<Comment> findByWishId(Long wishId);
}
