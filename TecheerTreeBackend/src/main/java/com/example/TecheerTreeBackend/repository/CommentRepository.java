package com.example.TecheerTreeBackend.repository;

import com.example.TecheerTreeBackend.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
