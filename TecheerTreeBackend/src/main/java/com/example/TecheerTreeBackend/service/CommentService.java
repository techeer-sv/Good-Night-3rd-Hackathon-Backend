package com.example.TecheerTreeBackend.service;

import com.example.TecheerTreeBackend.domain.Comment;
import com.example.TecheerTreeBackend.domain.Wish;
import com.example.TecheerTreeBackend.dto.CommentForm;
import com.example.TecheerTreeBackend.repository.CommentRepository;
import com.example.TecheerTreeBackend.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private WishRepository wishRepository;
    @Autowired
    private CommentRepository commentRepository;
    public String createComment(Long wishId, CommentForm commentForm) {

        // 소원 조회 및 예외처리
        Wish wish = wishRepository.findById(wishId)
                .orElseThrow(() -> new IllegalArgumentException("없는 소원입니다."));


        // 댓글 엔티티 생성
        Comment comment = Comment.createComment(wish, commentForm);

        // 엔티티를 DB에 저장
        commentRepository.save(comment);

        return "댓글 생성 성공!";
    }
}
