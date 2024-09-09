package com.TecheerTree.myproject.api.repository;

import com.TecheerTree.myproject.domain.entitiy.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comment,Long> {
    Page<Comment> findByWishIdAndDeletedAtFalse(Long wishId, Pageable pageable);
}
