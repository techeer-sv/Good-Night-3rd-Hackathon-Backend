package com.example.goodnight3rdhackathonbackend.repository;


import com.example.goodnight3rdhackathonbackend.domain.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository {
    void save(Comment comment);
    List<Comment> findAllByWishId(Long id, Pageable pageable);
    void updateById(Long id, Comment comment);
}
