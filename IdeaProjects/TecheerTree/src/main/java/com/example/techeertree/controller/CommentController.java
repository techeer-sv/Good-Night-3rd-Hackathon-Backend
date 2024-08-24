package com.example.techeertree.controller;

import com.example.techeertree.entity.Comment;
import com.example.techeertree.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


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
    // 특정 소원에 대한 댓글을 페이지네이션하여 조회하는 엔드포인트
    @GetMapping
    public ResponseEntity<Page<Comment>> getComments(@PathVariable Long wishId,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Comment> comments = commentService.getCommentsByWish(wishId, pageable);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

}