package com.example.techeertree.dto.wish;

import com.example.techeertree.domain.Category;
import com.example.techeertree.domain.Confirm;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class WishRequestDto {

    @NotBlank(message="제목은 필수 입력 항목입니다.")
    private String title;

    @NotBlank(message="내용은 필수 입력 항목입니다.")
    private String content;

    @NotNull(message="카테고리는 필수 선택 항목입니다.")
    private Category category;

    private LocalDateTime createdAt;

}



// 제목, 내용, 카테고리, 등록일, 승인 상태 정보를 포함해야 합니다.
// 카테고리의 종류는 진로, 건강, 인간 관계, 돈, 목표, 학업/성적, 기타 총 7가지입니다.
//is_confirm(소원 승인 상태)값의 초기값을 “보류됨” 으로 설정합니다.

//제목, 내용, 카테고리, 등록일 값이 하나라도 null일 경우 예외를 처리합니다.(추가기능)