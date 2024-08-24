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

    public void saveComment(CommentDto commentDto, Long wishId) {
        Comment comment = new Comment();
        comment.setWish_id(wishId);
        comment.setContent(commentDto.getContent());
        comment.setCreated_at(LocalDate.now());
        commentRepository.save(comment);
    }


    public List<CommentDto> findAllCommentByWishId(Long wishId, int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return commentRepository.findAll(pageable)
                .stream()
                .filter(comment -> wishId.equals(comment.getWish_id()))
                .map(comment -> {
                    CommentDto commentDto = new CommentDto();
                    commentDto.setContent(comment.getContent());
                    commentDto.setCreated_at(comment.getCreated_at());
                    return commentDto;
                })
                .toList();
    }

    public void deleteCommentById(Long commentId) {
        Comment targetComment = commentRepository.findById(commentId);
        targetComment.set_deleted(true);
        commentRepository.updateById(commentId, targetComment);
    }


}
