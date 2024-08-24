package com.example.techeertree.service;

import com.example.techeertree.entity.Comment;
import com.example.techeertree.entity.Wish;
import com.example.techeertree.exception.ResourceNotFoundException;
import com.example.techeertree.repository.CommentRepository;
import com.example.techeertree.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final WishRepository wishRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, WishRepository wishRepository) {
        this.commentRepository = commentRepository;
        this.wishRepository = wishRepository;
    }

    // 댓글을 추가하는 메서드
    public Comment addComment(Long wishId, String content) {
        // 주어진 ID에 해당하는 소원을 조회, 없으면 예외 발생
        Wish wish = wishRepository.findById(wishId)
                .orElseThrow(() -> new ResourceNotFoundException("Wish not found"));

        // 새로운 댓글 객체 생성 및 저장
        Comment comment = new Comment(content, wish);
        return commentRepository.save(comment);
    }

    // 특정 소원에 대한 댓글을 페이지네이션하여 조회하는 메서드
    public Page<Comment> getCommentsByWish(Long wishId, Pageable pageable) {
        Wish wish = wishRepository.findById(wishId)
                .orElseThrow(() -> new ResourceNotFoundException("Wish not found"));

        return commentRepository.findByWishAndIsDeletedFalse(wish, pageable);
    }
}