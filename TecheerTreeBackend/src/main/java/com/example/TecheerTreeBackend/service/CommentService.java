package com.example.TecheerTreeBackend.service;

import com.example.TecheerTreeBackend.domain.Comments;
import com.example.TecheerTreeBackend.domain.Wishes;
import com.example.TecheerTreeBackend.dto.CommentRequest;
import com.example.TecheerTreeBackend.dto.CommentListResponse;
import com.example.TecheerTreeBackend.repository.CommentRepository;
import com.example.TecheerTreeBackend.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final WishRepository wishRepository;

    private final CommentRepository commentRepository;
    public String createComment(Long wishId, CommentRequest commentRequest) {

        // 소원 조회 및 예외처리
        Wishes wishes = wishRepository.findById(wishId)
                .orElseThrow(() -> new IllegalArgumentException("없는 소원입니다."));


        // 댓글 엔티티 생성
        Comments comments = Comments.from(wishes, commentRequest);

        // 엔티티를 DB에 저장
        commentRepository.save(comments);

        return "댓글 생성 성공!";
    }

    public List<CommentListResponse> viewCommentList(Long wishId) {
        // 소원 조회 및 예외처리
        Wishes wishes = wishRepository.findById(wishId)
                .orElseThrow(() -> new IllegalArgumentException("없는 소원입니다."));

        // 댓글 리스트 조회 및 엔티티 -> DTO 변환
        return commentRepository.findByWishId(wishId).stream()
                .map(CommentListResponse :: createComment)
                .collect(Collectors.toList());
    }

    public String deleteComment(Long commentId) {
        // 댓글 조회
        Comments comments = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 commentId의 댓글을 찾을 수 없습니다."));

        // soft delete 처리
        comments.softDelete();

        // 처리 후 DB 저장
        commentRepository.save(comments);

        return "soft delete 처리가 되었습니다.";
    }
}
