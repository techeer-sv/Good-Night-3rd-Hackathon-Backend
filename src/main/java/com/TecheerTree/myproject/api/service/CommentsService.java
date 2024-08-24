package com.TecheerTree.myproject.api.service;

import com.TecheerTree.myproject.api.repository.CommentsRepository;
import com.TecheerTree.myproject.domain.dto.CommentCreateDto;
import com.TecheerTree.myproject.domain.entitiy.Comments;
import com.TecheerTree.myproject.domain.entitiy.Wishes;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentsService {

    private final CommentsRepository commentsRepository;

    public Comments createComment(CommentCreateDto commentCreateDto) {
        Comments comment = new Comments();
        comment.setContent(commentCreateDto.getContent());
        comment.setWishId(commentCreateDto.getWishId());
        comment.setCreatedDate(LocalDate.now());

        return commentsRepository.save(comment);
    }

    public void deleteComment(Long commentId) {
        // soft delete이기에 실제로 삭제 X
        Comments findComment = commentsRepository.findById(commentId).orElseThrow(()
                -> new EntityNotFoundException("comment not found with id: " + commentId));
        // deleted_at만 true로 변경
        findComment.setDeleted_at(true);

        commentsRepository.save(findComment);
    }
}
