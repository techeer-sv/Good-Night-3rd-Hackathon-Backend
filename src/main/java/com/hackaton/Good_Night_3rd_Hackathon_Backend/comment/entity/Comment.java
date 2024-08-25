package com.hackaton.Good_Night_3rd_Hackathon_Backend.comment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Comment {
    private Long comment_id;
    private String content;
    private java.sql.Timestamp registrationDate;
    private Long wish_id;
    private java.sql.Timestamp deleted_at;
    private boolean is_deleted;
}
