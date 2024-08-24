package com.example.goodnight3rdhackathonbackend.dto;


import lombok.Data;

import java.time.LocalDate;
@Data
public class WishDto {
    private String title;
    private String content;
    private String category;
    private LocalDate created_at;
    private String is_confirm;
}
