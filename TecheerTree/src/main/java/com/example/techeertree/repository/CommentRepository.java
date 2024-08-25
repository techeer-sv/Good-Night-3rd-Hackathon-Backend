package com.example.techeertree.repository;

import com.example.techeertree.domain.Comment;
import com.example.techeertree.domain.Wish;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;


public interface CommentRepository extends JpaRepository<Comment, Long> {
   Page<Comment> findByWishAndIsDeletedFalse(Wish wish, Pageable pageable);

}
