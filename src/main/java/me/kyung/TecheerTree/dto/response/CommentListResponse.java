package me.kyung.TecheerTree.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.kyung.TecheerTree.domain.Comment;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentListResponse {
    private Long id;
    private String content;
    private LocalDateTime createDate;

    public static CommentListResponse from(Comment comment){
        return new CommentListResponse(comment.getId(),comment.getContent(), comment.getCreatedDate());
    }
}
