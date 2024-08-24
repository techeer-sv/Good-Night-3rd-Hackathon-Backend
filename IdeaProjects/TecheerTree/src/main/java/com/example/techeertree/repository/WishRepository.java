package com.example.techeertree.repository;

import com.example.techeertree.entity.Wish;
import com.example.techeertree.entity.Wish.ApprovalStatus;
import com.example.techeertree.entity.Wish.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {
    Page<Wish> findAllByIsDeletedFalseAndIsConfirm(ApprovalStatus isConfirm, Pageable pageable);
    List<Wish> findByTitleContainingAndContentContainingAndCategoryAndIsDeletedFalse(String title, String content, Category category, Pageable pageable);
    Page<Wish> findAllByIsDeletedFalse(Pageable pageable);

    List<Wish> findByTitleContainingAndContentContainingAndIsDeletedFalse(String keyword, String keyword1, Pageable pageable);
}

