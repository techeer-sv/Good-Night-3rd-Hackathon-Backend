package com.example.goodnight3rdhackathonbackend.controller;

import com.example.goodnight3rdhackathonbackend.dto.CommentDto;
import com.example.goodnight3rdhackathonbackend.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comments/{wishId}")
    public void saveComment(@RequestBody CommentDto commentDto, @PathVariable Long wishId) {
        commentService.saveComment(commentDto, wishId);
    }

    @GetMapping("/comments/{wishId}")
    public List<CommentDto> findAllCommentByWishId(@RequestParam(value = "page", defaultValue = "0") int page,
                                                   @PathVariable Long wishId) {
        return commentService.findAllCommentByWishId(wishId, page);
    }


}
