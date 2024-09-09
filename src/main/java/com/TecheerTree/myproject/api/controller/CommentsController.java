package com.TecheerTree.myproject.api.controller;

import com.TecheerTree.myproject.api.service.CommentsService;
import com.TecheerTree.myproject.domain.dto.request.CommentSaveRequest;
import com.TecheerTree.myproject.domain.entitiy.Comments;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentsController {

    private final CommentsService commentsService;

    // 댓글 작성
    @PostMapping
    public ResponseEntity<Comments> createComment(@RequestBody CommentSaveRequest commentSaveRequest){
        //Comments comment = commentsService.createComment(commentSaveRequest);
        // service로 전달할 때는 요청 객체 -> 도메인 객체로 변환하여 전달
        Comments comment = commentsService.createComment(Comments.fromDTO(commentSaveRequest));
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteMember(@PathVariable("commentId") Long commentId) {
        commentsService.deleteComment(commentId);
        return ResponseEntity.ok("삭제에 성공하였습니다.");
    }

    // 댓글 조회
    @GetMapping
    public ResponseEntity<Page<Comments>> getComments(@RequestParam("wishId") Long wishId,
                                      @PageableDefault(sort = "createdDate", direction = org.springframework.data.domain.Sort.Direction.DESC) Pageable pageable) {
        Page<Comments> comments = commentsService.getComments(wishId, pageable);
        return new ResponseEntity<>(comments,HttpStatus.OK);
    }



}


