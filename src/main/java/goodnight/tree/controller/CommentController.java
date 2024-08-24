package goodnight.tree.controller;


import goodnight.tree.domain.dto.request.CommentSaveRequest;
import goodnight.tree.domain.dto.response.CommentResponse;
import goodnight.tree.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> savePost(@RequestBody CommentSaveRequest request) {
        commentService.saveComment(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}