package com.techeer.tree.Good_Night_3rd_Hackathon_Backend.repository;

import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByWishId(Long wishId);
}