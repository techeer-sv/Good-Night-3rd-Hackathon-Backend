package com.example.Good_Night_3rd_Hackathon_Backend.controller;

import com.example.Good_Night_3rd_Hackathon_Backend.domain.Comments;
import com.example.Good_Night_3rd_Hackathon_Backend.dto.CommentDto;
import com.example.Good_Night_3rd_Hackathon_Backend.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentsController {
    private final CommentsService commentsService;

    @Autowired
    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @PostMapping("/comments/{wishId}")
    public ResponseEntity<String> createComment(@PathVariable Long wishId, @RequestBody CommentDto commentDto) {
        try {
            Long id = commentsService.createComment(wishId, commentDto.getContent());
            return ResponseEntity.status(HttpStatus.CREATED).body("Comments successfully created. id: " + id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create comment.");
        }
    }
}
