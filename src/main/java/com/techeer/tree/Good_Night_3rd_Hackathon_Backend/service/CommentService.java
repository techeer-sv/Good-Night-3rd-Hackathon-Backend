package com.techeer.tree.Good_Night_3rd_Hackathon_Backend.service;

import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.dto.request.CommentRequest;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.entity.Comment;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.entity.Wish;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.repository.CommentRepository;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.repository.WishRepository;
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
  public Comment createComment(Long wishId, CommentRequest.CommentCreateRequest request) {
    Wish wish = wishRepository.findById(wishId)
        .orElseThrow(() -> new IllegalArgumentException("Wish not found with id " + wishId));

    Comment comment = new Comment(request.getContent(), wish);
    return commentRepository.save(comment);
  }
}