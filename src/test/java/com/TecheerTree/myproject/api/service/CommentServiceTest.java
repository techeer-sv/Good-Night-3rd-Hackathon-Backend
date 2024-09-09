package com.TecheerTree.myproject.api.service;

import com.TecheerTree.myproject.api.controller.CommentsController;
import com.TecheerTree.myproject.api.repository.CommentsRepository;
import com.TecheerTree.myproject.domain.dto.request.CommentSaveRequest;
import com.TecheerTree.myproject.domain.entitiy.Comment;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private CommentsController commentsController;

    @Autowired
    private CommentsRepository commentsRepository;

    private CommentSaveRequest commentSaveRequest;

    @BeforeEach
    void setUp(){
        commentSaveRequest = new CommentSaveRequest();
        commentSaveRequest.setContent("테스트용 댓글입니다");
        commentSaveRequest.setWishId(13L);
    }

    // 댓글 등록 테스트
    @Test
    void createComment() {
        Comment comment = commentsService.createComment(Comment.fromDTO(commentSaveRequest));
        assertNotNull(comment);
    }

    // 댓글 삭제 테스트
    @Test
    void deleteComment(){
        Long commentId = 1L; // 존재하는 wishId로 변경
        commentsService.deleteComment(commentId);

        Comment comment = commentsRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("comment not found with id: " + commentId));

        assertTrue(comment.isDeletedAt()); // true로 저장되었는지 확인
    }
}