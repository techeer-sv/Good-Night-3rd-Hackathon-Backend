package com.example.TecheerTreeBackend.controller;


import com.example.TecheerTreeBackend.dto.CommentForm;
import com.example.TecheerTreeBackend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 댓글 생성
    @PostMapping("/comment/{wishId}")
    public String createComment(@PathVariable Long wishId, @RequestBody CommentForm commentForm){
        // 서비스 위임
        return commentService.createComment(wishId, commentForm);
    }

    // 댓글 리스트 조회
    @GetMapping("/comment/{wishId}")
    public ResponseEntity<List<CommentForm>> viewCommentList(@PathVariable Long wishId){
        // 서비스 위임
        List<CommentForm> viewCommentList = commentService.viewCommentList(wishId);

        // 댓글 리스트 출력
        return ResponseEntity.status(HttpStatus.OK).body(viewCommentList);
    }
}
