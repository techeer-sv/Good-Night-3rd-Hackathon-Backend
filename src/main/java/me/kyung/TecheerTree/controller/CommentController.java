package me.kyung.TecheerTree.controller;


import lombok.RequiredArgsConstructor;
import me.kyung.TecheerTree.domain.Comment;
import me.kyung.TecheerTree.dto.request.CommentSaveRequest;
import me.kyung.TecheerTree.dto.response.CommentListResponse;
import me.kyung.TecheerTree.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody CommentSaveRequest request){
        return ResponseEntity.ok(commentService.saveComment(request));
    }

    @GetMapping("/{wishId}")
    public ResponseEntity<List<CommentListResponse>> findAllComment(@PathVariable("wishId") Long wishId,@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {

        List<CommentListResponse> commentList = commentService.findAllComment(wishId,page, size);
        return ResponseEntity.ok(commentList);
    }
}
