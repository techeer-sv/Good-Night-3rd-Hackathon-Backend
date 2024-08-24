package com.example.Good_Night_3rd_Hackathon_Backend.controller;

import com.example.Good_Night_3rd_Hackathon_Backend.dto.CommentDto;
import com.example.Good_Night_3rd_Hackathon_Backend.service.CommentsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Comments", description = "댓글 API")
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

    @GetMapping("/comments/{wishId}")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable Long wishId, @RequestParam int page, @RequestParam int size) {
        try {
            Page<CommentDto> commentsPage = commentsService.getComments(wishId, page, size);
            return ResponseEntity.status(HttpStatus.OK).body(commentsPage.getContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<String> deleteWishes(@PathVariable Long id) {
        try {
            commentsService.deleteComment(id);
            return ResponseEntity.status(HttpStatus.OK).body("Comments successfully deleted.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete comments.");
        }
    }
}
