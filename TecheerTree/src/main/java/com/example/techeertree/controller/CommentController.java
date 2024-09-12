package com.example.techeertree.controller;

import com.example.techeertree.domain.Comment;
import com.example.techeertree.dto.comment.CommentMapper.*;
import com.example.techeertree.dto.comment.CommentRequestDto.*;
import com.example.techeertree.dto.comment.CommentResponseDto.*;
import com.example.techeertree.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/comments")
public class CommentController {
    private final CommentService commentService;

    @Operation(summary = "댓글 등록")
    @PostMapping("/{id}")
    public ResponseEntity<CommentInfoResponseDto> createComment(@PathVariable Long id, @Valid @RequestBody CommentCreateRequestDto commentCreateRequestDto){
        Comment comment =  commentService.create(id, commentCreateRequestDto);
        CommentInfoResponseDto responseDto = CommentCreateMapper.INSTANCE.toDto(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @Operation(summary = "댓글 조회")
    @GetMapping("/{id}")
    public ResponseEntity<Page<CommentInfoResponseDto>> getComments(@PathVariable Long id,
                                                                    @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable){

        Page<Comment> comments = commentService.getComments(id, pageable);

        Page<CommentInfoResponseDto> responseDtoPage = comments.map(CommentCreateMapper.INSTANCE::toDto);
       // Page<CommentInfoResponseDto> comments = commentService.getComments(id, page, size);
        return ResponseEntity.status(HttpStatus.OK).body(responseDtoPage);
    }

    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId){
        commentService.softDelete(commentId);
        return ResponseEntity.noContent().build();
    }

}
