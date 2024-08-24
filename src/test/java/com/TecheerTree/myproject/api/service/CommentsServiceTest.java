package com.TecheerTree.myproject.api.service;

import com.TecheerTree.myproject.api.controller.CommentsController;
import com.TecheerTree.myproject.api.repository.CommentsRepository;
import com.TecheerTree.myproject.domain.dto.CommentCreateDto;
import com.TecheerTree.myproject.domain.entitiy.Comments;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentsServiceTest {

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private CommentsController commentsController;

    @Autowired
    private CommentsRepository commentsRepository;

    private CommentCreateDto commentCreateDto;

    @BeforeEach
    void setUp(){
        commentCreateDto = new CommentCreateDto();
        commentCreateDto.setContent("테스트용 댓글입니다");
        commentCreateDto.setWishId(13L);
    }

    // 댓글 등록 테스트
    @Test
    void createComment() {
        Comments comment = commentsService.createComment(commentCreateDto);
        assertNotNull(comment);
    }

    // 댓글 삭제 테스트
    @Test
    void deleteComment(){
        Long commentId = 1L; // 존재하는 wishId로 변경
        commentsService.deleteComment(commentId);

        Comments comment = commentsRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("comment not found with id: " + commentId));

        assertTrue(comment.isDeletedAt()); // true로 저장되었는지 확인
    }
}