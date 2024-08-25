package com.example.techeertree.controller;

import com.example.techeertree.dto.comment.CommentRequestDto.*;
import com.example.techeertree.dto.comment.CommentResponseDto.*;
import com.example.techeertree.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/comments")
public class CommentController {
    private final CommentService commentService;

    @Operation(summary = "댓글 등록")
    @PostMapping("/{wishId}")
    public ResponseEntity<CommentInfoResponseDto> createComment(@PathVariable Long wishId, @Valid @RequestBody CommentCreateRequestDto commentCreateRequestDto){
        CommentInfoResponseDto responseDto = commentService.create(wishId, commentCreateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId){
        commentService.softDelete(commentId);
        return ResponseEntity.noContent().build();
    }

}
