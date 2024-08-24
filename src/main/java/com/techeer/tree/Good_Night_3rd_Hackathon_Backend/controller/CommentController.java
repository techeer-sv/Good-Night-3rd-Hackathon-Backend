package com.techeer.tree.Good_Night_3rd_Hackathon_Backend.controller;

import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.dto.request.CommentRequest;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.dto.response.CommentResponse;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.entity.Comment;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
@AllArgsConstructor
@Tag(name = "Comment", description = "Comment management API")
public class CommentController {

  private final CommentService commentService;

  @Operation(summary = "Create a new comment", description = "Create a new comment for a specific wish")
  @PostMapping("/{wish_id}")
  public ResponseEntity<CommentResponse.CommentCreateResponse> createComment(
      @PathVariable Long wish_id,
      @RequestBody @Valid CommentRequest.CommentCreateRequest request) {

    Comment comment = commentService.createComment(wish_id, request);
    try {
      CommentResponse.CommentCreateResponse response = CommentResponse.CommentCreateResponse.success(comment);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      CommentResponse.CommentCreateResponse response = CommentResponse.CommentCreateResponse.failure(comment,"댓글 생성 실패: " + e.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
  }

  @Operation(summary = "Delete a comment", description = "Delete a comment by marking it as deleted (soft delete)")
  @DeleteMapping("/{comment_id}")
  public ResponseEntity<CommentResponse.CommentDeleteResponse> deleteComment(@PathVariable Long comment_id) {
    try {
      commentService.deleteComment(comment_id);
      CommentResponse.CommentDeleteResponse response = CommentResponse.CommentDeleteResponse.success(comment_id);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      CommentResponse.CommentDeleteResponse response = CommentResponse.CommentDeleteResponse.failure(comment_id, "댓글 삭제 실패: " + e.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
  }
}