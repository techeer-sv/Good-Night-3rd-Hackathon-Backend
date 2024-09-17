package com.example.goodnight3rdhackathonbackend.controller;

import com.example.goodnight3rdhackathonbackend.domain.Comment;
import com.example.goodnight3rdhackathonbackend.dto.CommentDto;
import com.example.goodnight3rdhackathonbackend.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comments")
    public void saveComment(@RequestBody CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setWishId(commentDto.getWishId());
        comment.setContent(commentDto.getContent());
        comment.setCreatedAt(LocalDate.now());
        commentService.saveComment(comment);
    }

    @GetMapping("/comments")
    public List<CommentDto> findAllCommentByWishId(@RequestParam(value = "page", defaultValue = "0") int page, Long wishId) {
        return commentService.findAllCommentByWishId(wishId, page);
    }

    @DeleteMapping("/comment/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteCommentById(commentId);
    }


}
