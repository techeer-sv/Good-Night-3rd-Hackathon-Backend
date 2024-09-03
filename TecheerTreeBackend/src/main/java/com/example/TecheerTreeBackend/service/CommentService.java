package com.example.TecheerTreeBackend.service;

import com.example.TecheerTreeBackend.domain.Comments;
import com.example.TecheerTreeBackend.domain.Wishes;
import com.example.TecheerTreeBackend.dto.CommentForm;
import com.example.TecheerTreeBackend.dto.CommentListResponse;
import com.example.TecheerTreeBackend.repository.CommentRepository;
import com.example.TecheerTreeBackend.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final WishRepository wishRepository;

    private final CommentRepository commentRepository;
    public String createComment(Long wishId, CommentForm commentForm) {

        // 소원 조회 및 예외처리
        Wishes wishes = wishRepository.findById(wishId)
                .orElseThrow(() -> new IllegalArgumentException("없는 소원입니다."));


        // 댓글 엔티티 생성
        Comments comments = Comments.createComment(wishes, commentForm);

        // 엔티티를 DB에 저장
        commentRepository.save(comments);

        return "댓글 생성 성공!";
    }

    public List<CommentListResponse> viewCommentList(Long wishId) {
        // 소원 조회 및 예외처리
        Wishes wishes = wishRepository.findById(wishId)
                .orElseThrow(() -> new IllegalArgumentException("없는 소원입니다."));

        // 댓글 리스트 조회
        List<Comments> comments = commentRepository.findByWishId(wishId);

        // 엔티티 -> DTO 변환
        List<CommentListResponse> dtos = new ArrayList<CommentListResponse>();
        for (int i = 0; i < comments.size(); i++){          // 조회한 일정 엔티티 수 만큼 반복
            Comments c = comments.get(i);                    // 조회한 일정 엔티티 하나씩 가져오기
            CommentListResponse dto = CommentListResponse.createComment(c); // 엔티티를 DTO로 변환
            dtos.add(dto);
        }

        return dtos;
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
