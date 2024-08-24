package com.example.techeertree.repository;

import com.example.techeertree.entity.Comment;
import com.example.techeertree.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 특정 소원에 속한 삭제되지 않은 댓글을 페이지네이션하여 조회
    Page<Comment> findByWishAndIsDeletedFalse(Wish wish, Pageable pageable);
}
