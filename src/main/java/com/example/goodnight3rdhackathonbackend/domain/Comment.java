package com.example.goodnight3rdhackathonbackend.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Comment {
    private Long commentId;
    private Long wishId;
    private String content;
    private LocalDate createdAt;
    private boolean isDeleted = false;

}
