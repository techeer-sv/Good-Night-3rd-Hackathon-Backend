package com.example.goodnight3rdhackathonbackend.service;


import com.example.goodnight3rdhackathonbackend.domain.Comment;
import com.example.goodnight3rdhackathonbackend.dto.CommentDto;
import com.example.goodnight3rdhackathonbackend.repository.CommentRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void saveComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setWishId(commentDto.getWishId());
        comment.setContent(commentDto.getContent());
        comment.setCreatedAt(LocalDate.now());
        commentRepository.save(comment);
    }


    public List<CommentDto> findAllCommentByWishId(Long wishId, int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return commentRepository.findAll(pageable)
                .stream()
                .filter(comment -> wishId.equals(comment.getWishId()))
                .map(comment -> {
                    CommentDto commentDto = new CommentDto();
                    commentDto.setContent(comment.getContent());
                    commentDto.setCreatedAt(comment.getCreatedAt());
                    return commentDto;
                })
                .toList();
    }

    public void deleteCommentById(Long commentId) {
        Comment targetComment = commentRepository.findById(commentId);
        targetComment.setDeleted(true);
        commentRepository.updateById(commentId, targetComment);
    }


}
