package com.TecheerTree.myproject.api.service;

import com.TecheerTree.myproject.api.repository.CommentsRepository;
import com.TecheerTree.myproject.domain.entitiy.Comment;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CommentsService {

    private final CommentsRepository commentsRepository;

    @Transactional
    public Comment createComment(Comment comment) {
        return commentsRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        // soft delete이기에 실제로 삭제 X
        Comment findComment = commentsRepository.findById(commentId).orElseThrow(()
                -> new EntityNotFoundException("comment not found with id: " + commentId));
        // deleted_at만 true로 변경
        findComment.setDeletedAt(true);

        commentsRepository.save(findComment);
    }

    public Page<Comment> getComments(Long wishId, Pageable pageable) {
        return commentsRepository.findByWishIdAndDeletedAtFalse(wishId,pageable);
    }
}
