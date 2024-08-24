package me.kyung.TecheerTree.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.kyung.TecheerTree.domain.Comment;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentListResponse {
    private String content;
    private LocalDateTime createDate;

    public static CommentListResponse from(Comment comment){
        return new CommentListResponse(comment.getContent(), comment.getCreatedDate());
    }
}
