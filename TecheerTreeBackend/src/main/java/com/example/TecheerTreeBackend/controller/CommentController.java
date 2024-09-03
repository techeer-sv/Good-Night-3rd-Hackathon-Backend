package com.example.TecheerTreeBackend.controller;


import com.example.TecheerTreeBackend.dto.CommentForm;
import com.example.TecheerTreeBackend.dto.CommentListResponse;
import com.example.TecheerTreeBackend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping("/{wishId}")
    public String createComment(@PathVariable Long wishId, @RequestBody CommentForm commentForm){
        // 서비스 위임
        return commentService.createComment(wishId, commentForm);
    }

    // 댓글 리스트 조회
    @GetMapping("/{wishId}")
    public ResponseEntity<List<CommentListResponse>> viewCommentList(@PathVariable Long wishId){
        // 서비스 위임
        List<CommentListResponse> viewCommentList = commentService.viewCommentList(wishId);

        // 댓글 리스트 출력
        return ResponseEntity.status(HttpStatus.OK).body(viewCommentList);
    }

    // 댓글 삭제(soft delete)
    @PatchMapping("/{commentId}")
    public String deleteComment(@PathVariable Long commentId){
        // 서비스 위임
        return commentService.deleteComment(commentId);
    }
}