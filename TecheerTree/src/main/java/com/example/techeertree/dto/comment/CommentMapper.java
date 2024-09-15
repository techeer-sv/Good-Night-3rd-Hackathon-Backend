package com.example.techeertree.dto.comment;

import com.example.techeertree.domain.Comment;
import com.example.techeertree.dto.EntityMapper;
import com.example.techeertree.dto.comment.CommentRequestDto.*;
import com.example.techeertree.dto.comment.CommentResponseDto.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

public interface CommentMapper {

    @Mapper
    interface CommentCreateMapper extends EntityMapper<CommentCreateRequestDto, CommentInfoResponseDto, Comment>{
        CommentCreateMapper INSTANCE = Mappers.getMapper(CommentCreateMapper.class);

        @Override
        default Comment toEntity(CommentCreateRequestDto createRequestDto){
            if(createRequestDto == null){
                return null;
            }
            return Comment.builder()
                    .comment(createRequestDto.getComment())
                    .build();
        };

        @Override
        default CommentInfoResponseDto toDto(Comment comment){
            if(comment == null){
                return null;
            }

            return CommentInfoResponseDto.builder()
                    .id(comment.getId())
                    .comment(comment.getComment())
                    .wishId(comment.getWishEntity().getId())
                    .createdAt(comment.getCreatedAt())
                    .build();
        }
    }
}
