package com.TecheerTree.myproject.api.service;

import com.TecheerTree.myproject.api.repository.CommentsRepository;
import com.TecheerTree.myproject.domain.dto.CommentCreateDto;
import com.TecheerTree.myproject.domain.entitiy.Comments;
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

    }
}
