package com.techeer.tree.Good_Night_3rd_Hackathon_Backend.service;

import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.dto.request.CommentRequest;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.dto.request.CommentRequest.CommentCreateRequest;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.dto.response.CommentResponse;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.dto.response.CommentResponse.CommentListResponse;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.entity.Comment;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.entity.Wish;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.repository.CommentRepository;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.repository.WishRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

  private final WishRepository wishRepository;
  private final CommentRepository commentRepository;

  public CommentService(WishRepository wishRepository, CommentRepository commentRepository) {
    this.wishRepository = wishRepository;
    this.commentRepository = commentRepository;
  }

  @Transactional
  public Comment createComment(Long wishId, CommentCreateRequest request) {
    Wish wish = wishRepository.findById(wishId)
        .orElseThrow(() -> new IllegalArgumentException("Wish not found with id " + wishId));

    Comment comment = new Comment(request.getContent(), wish);
    return commentRepository.save(comment);
  }

  @Transactional
  public void deleteComment(Long wishId) {
    Comment comment = commentRepository.findById(wishId)
        .orElseThrow(() -> new IllegalArgumentException("Comment not found with id " + wishId));
    comment.setIsDeleted(true); // soft delete
    commentRepository.save(comment); // 변경 사항 저장
  }

  @Transactional(readOnly = true)
  public List<CommentResponse.CommentListResponse> getAllComments(Long wishId) {
    List<Comment> comments;
    if (wishId != null) {
      comments = commentRepository.findByWishId(wishId);
    } else {
      comments = commentRepository.findAll();
    }
    return comments.stream()
        .map(CommentResponse.CommentListResponse::from)
        .collect(Collectors.toList());
  }
}