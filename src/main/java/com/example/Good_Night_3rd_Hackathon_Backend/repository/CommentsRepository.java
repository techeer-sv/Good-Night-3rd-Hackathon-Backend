package com.example.Good_Night_3rd_Hackathon_Backend.repository;

import com.example.Good_Night_3rd_Hackathon_Backend.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
}
