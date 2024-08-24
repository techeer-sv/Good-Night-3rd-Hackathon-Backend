package com.example.goodnight3rdhackathonbackend.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Comment {
    private Long comment_id;
    private Long wish_id;
    private String content;
    private LocalDate created_at;
    private boolean is_deleted = false;

}
