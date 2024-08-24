package me.kyung.TecheerTree.controller;

import lombok.RequiredArgsConstructor;
import me.kyung.TecheerTree.domain.Comment;
import me.kyung.TecheerTree.dto.request.CommentSaveRequest;
import me.kyung.TecheerTree.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody CommentSaveRequest request){
        return ResponseEntity.ok(commentService.saveComment(request));
    }
}
