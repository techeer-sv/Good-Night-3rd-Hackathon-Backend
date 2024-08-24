package me.kyung.TecheerTree.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kyung.TecheerTree.domain.Comment;
import me.kyung.TecheerTree.domain.Wish;
import me.kyung.TecheerTree.dto.request.CommentSaveRequest;
import me.kyung.TecheerTree.repository.CommentRepository;
import me.kyung.TecheerTree.repository.WishRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final WishRepository wishRepository;

    public Comment saveComment(CommentSaveRequest request){
        Wish wish = wishRepository.findById(request.getWishId()).orElseThrow(
                () -> new RuntimeException("존재하지 않는 게시글입니다.")
        );
        Comment Comment = request.toEntity(wish);
        return commentRepository.save(Comment);
    }
}
