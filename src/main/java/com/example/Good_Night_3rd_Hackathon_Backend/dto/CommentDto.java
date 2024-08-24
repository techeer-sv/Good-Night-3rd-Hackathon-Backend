package com.example.Good_Night_3rd_Hackathon_Backend.dto;

import com.example.Good_Night_3rd_Hackathon_Backend.domain.Comments;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private Long id;
    private String content;
    private String createdAt;

    public CommentDto(Comments comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt().toString();
    }
}
