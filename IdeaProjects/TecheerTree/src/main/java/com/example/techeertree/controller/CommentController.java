package com.example.techeertree.controller;

import com.example.techeertree.entity.Comment;
import com.example.techeertree.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/wishes/{wishId}/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 새로운 댓글을 추가하는 엔드포인트
    @PostMapping
    public ResponseEntity<Comment> addComment(@PathVariable Long wishId, @RequestBody String content) {
        // 댓글 생성 후, 생성된 댓글을 반환
        Comment comment = commentService.addComment(wishId, content);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }
}