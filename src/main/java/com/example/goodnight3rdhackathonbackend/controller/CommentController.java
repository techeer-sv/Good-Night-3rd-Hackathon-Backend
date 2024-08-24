package com.example.goodnight3rdhackathonbackend.controller;

import com.example.goodnight3rdhackathonbackend.dto.CommentDto;
import com.example.goodnight3rdhackathonbackend.service.CommentService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comments/{wishId}")
    public void saveComment(@RequestBody CommentDto commentDto, @PathVariable Long wishId) {
        commentService.saveComment(commentDto, wishId);
    }
}
