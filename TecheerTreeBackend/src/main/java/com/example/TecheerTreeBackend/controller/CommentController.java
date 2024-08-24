package com.example.TecheerTreeBackend.controller;


import com.example.TecheerTreeBackend.dto.CommentForm;
import com.example.TecheerTreeBackend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
