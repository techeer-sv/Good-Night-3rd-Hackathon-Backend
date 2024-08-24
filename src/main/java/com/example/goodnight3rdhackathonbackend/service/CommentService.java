package com.example.goodnight3rdhackathonbackend.service;


import com.example.goodnight3rdhackathonbackend.domain.Comment;
import com.example.goodnight3rdhackathonbackend.dto.CommentDto;
import com.example.goodnight3rdhackathonbackend.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void saveComment(CommentDto commentDto, Long wishId) {
        Comment comment = new Comment();
        comment.setWish_id(wishId);
        comment.setContent(commentDto.getContent());
        comment.setCreated_at(LocalDate.now());
        commentRepository.save(comment);
    }

}
