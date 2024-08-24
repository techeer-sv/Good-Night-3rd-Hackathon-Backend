package com.example.Good_Night_3rd_Hackathon_Backend.repository;

import com.example.Good_Night_3rd_Hackathon_Backend.domain.Comments;
import com.example.Good_Night_3rd_Hackathon_Backend.domain.ConfirmStatus;
import com.example.Good_Night_3rd_Hackathon_Backend.domain.Wishes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    Page<Comments> findAllByWishIdAndIsDeletedFalse(Long wishId, Pageable pageable);
}
