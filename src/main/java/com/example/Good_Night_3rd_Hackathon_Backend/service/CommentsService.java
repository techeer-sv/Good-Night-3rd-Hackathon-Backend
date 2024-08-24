package com.example.Good_Night_3rd_Hackathon_Backend.service;

import com.example.Good_Night_3rd_Hackathon_Backend.domain.Comments;
import com.example.Good_Night_3rd_Hackathon_Backend.domain.Wishes;
import com.example.Good_Night_3rd_Hackathon_Backend.repository.CommentsRepository;
import com.example.Good_Night_3rd_Hackathon_Backend.repository.WishesRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.Optional;

@Transactional
public class CommentsService {
    private final CommentsRepository commentsRepository;
    private final WishesRepository wishesRepository;

    public CommentsService(CommentsRepository commentsRepository, WishesRepository wishesRepository) {
        this.commentsRepository = commentsRepository;
        this.wishesRepository = wishesRepository;
    }

    public void deleteComment(Long id) {
        Optional<Comments> comment = commentsRepository.findById(id);
        if (comment.isPresent()) {
            Comments foundComment = comment.get();
            foundComment.setDeleted(true);  // 소프트 삭제 플래그 설정
            commentsRepository.save(foundComment);  // 엔티티 업데이트
        } else {
            throw new EntityNotFoundException("Comment not found with id: " + id);
        }
        commentsRepository.deleteById(id);
    }

    public Long createComment(Long wishId, String content) {
        Wishes wish = wishesRepository.findById(wishId).orElseThrow(() -> new EntityNotFoundException("Wish not found with id: " + wishId));
        Comments comment = new Comments(wish, content);
        return commentsRepository.save(comment).getId();
    }

}
