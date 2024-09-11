package com.example.techeertree.service;

import com.example.techeertree.domain.Comment;
import com.example.techeertree.domain.Confirm;
import com.example.techeertree.domain.WishEntity;
import com.example.techeertree.dto.comment.CommentMapper.*;
import com.example.techeertree.dto.comment.CommentRequestDto.*;
import com.example.techeertree.dto.comment.CommentResponseDto.*;

import com.example.techeertree.exception.BaseException;
import com.example.techeertree.exception.ErrorCode;
import com.example.techeertree.repository.CommentRepository;
import com.example.techeertree.repository.WishEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final WishEntityRepository wishRepository;


    public CommentInfoResponseDto create(Long id, CommentCreateRequestDto commentCreateRequestDto) {
        // 소원 존재 유무 조회
        WishEntity wish = wishRepository.findById(id).orElseThrow(() -> new BaseException(ErrorCode.NOT_EXIST_WISH));

        if(wish.getIsConfirm() != Confirm.CONFIRM)
            throw new BaseException(ErrorCode.NOT_EXIST_WISH);


        // 댓글 객체 생성
        Comment comment = CommentCreateMapper.INSTANCE.toEntity(commentCreateRequestDto);
        comment.matchWish(wish);
        commentRepository.save(comment);

        // 소원에 댓글 등록
        wish.getComments().add(comment);
        wishRepository.save(wish);

        return CommentCreateMapper.INSTANCE.toDto(comment);
    }

    @Transactional(readOnly = true)
    public Page<CommentInfoResponseDto> getComments(Long wishId, int page, int size){
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(page, size, sort);

        WishEntity wish = wishRepository.findById(wishId)
                .filter(w -> {
                    if(w.getIsConfirm() != Confirm.CONFIRM) {
                        throw new BaseException(ErrorCode.NOT_CONFIRMED);
                    }
                    return true;
                })
                .filter(w -> {
                    if(w.isDeleted()){
                        throw new BaseException(ErrorCode.NOT_EXIST_WISH);
                    }
                    return true;
                })
                .orElseThrow(()-> new BaseException(ErrorCode.NOT_EXIST_WISH));

        Page<Comment> comments = commentRepository.findByWishEntityAndIsDeletedFalse(wish, pageable);

        return comments.map(CommentCreateMapper.INSTANCE::toDto);
    }

    public void softDelete(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new BaseException(ErrorCode.NOT_EXIST_COMMENT));
        comment.setDeleted();

        commentRepository.save(comment);
    }
}