package com.example.techeertree.repository;

import com.example.techeertree.entity.Comment;
import com.example.techeertree.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByWish(Wish wish);
}
