package com.example.Good_Night_3rd_Hackathon_Backend.service;

import com.example.Good_Night_3rd_Hackathon_Backend.domain.Comments;
import com.example.Good_Night_3rd_Hackathon_Backend.domain.ConfirmStatus;
import com.example.Good_Night_3rd_Hackathon_Backend.domain.Wishes;
import com.example.Good_Night_3rd_Hackathon_Backend.dto.CommentDto;
import com.example.Good_Night_3rd_Hackathon_Backend.dto.WishesListDto;
import com.example.Good_Night_3rd_Hackathon_Backend.repository.CommentsRepository;
import com.example.Good_Night_3rd_Hackathon_Backend.repository.WishesRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
    }

    public Long createComment(Long wishId, String content) {
        Wishes wish = wishesRepository.findById(wishId).orElseThrow(() -> new EntityNotFoundException("Wish not found with id: " + wishId));
        Comments comment = new Comments(wish, content);
        return commentsRepository.save(comment).getId();
    }

    public Page<CommentDto> getComments(Long id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Comments> comments = commentsRepository.findAllByWishIdAndIsDeletedFalse(id, pageable);
        return comments.map(CommentDto::new);
    }

}
