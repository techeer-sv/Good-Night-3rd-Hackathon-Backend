package com.example.goodnight3rdhackathonbackend.domain;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class Wish {
    private Long id;
    private String title;
    private String content;
    private String category;
    private LocalDate created_at;
    private String is_confirm = WishConfirmState.PENDING.getKorean();
    private boolean is_deleted;
}
