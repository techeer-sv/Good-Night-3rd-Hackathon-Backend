package com.TecheerTree.myproject.api.controller;

import com.TecheerTree.myproject.api.service.CommentsService;
import com.TecheerTree.myproject.domain.dto.CommentCreateDto;
import com.TecheerTree.myproject.domain.entitiy.Comments;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentsController {

    private final CommentsService commentsService;

    @PostMapping
    public ResponseEntity<Comments> createComment(@RequestBody CommentCreateDto commentCreateDto){
        Comments comment = commentsService.createComment(commentCreateDto);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteMember(@PathVariable("commentId") Long commentId) {
        commentsService.deleteComment(commentId);
        return ResponseEntity.ok("삭제에 성공하였습니다.");
    }}
