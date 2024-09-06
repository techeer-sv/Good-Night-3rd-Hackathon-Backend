package com.example.TecheerTreeBackend.repository;

import com.example.TecheerTreeBackend.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comments, Long> {

    @Query(value = "SELECT * FROM comments WHERE wish_id =:wishId AND is_deleted = FALSE", nativeQuery = true)
    List<Comments> findByWishId(Long wishId);
}
