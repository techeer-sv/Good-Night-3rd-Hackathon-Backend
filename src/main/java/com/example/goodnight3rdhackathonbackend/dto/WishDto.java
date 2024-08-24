package com.example.goodnight3rdhackathonbackend.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class WishDto {

    @Data
    public static class SaveDto {
        private String title;
        private String content;
        private String category;
        private LocalDate created_at;
    }


    @Data
    public static class FindDto {
        private String title;
        private String content;
        private String category;
    }

    @Data
    public static class FindAllDto {
        private String title;
        private String category;
        private LocalDate created_at;
    }

    @Data
    public static class ConfirmDto {
        private String is_confirm;
    }
}
