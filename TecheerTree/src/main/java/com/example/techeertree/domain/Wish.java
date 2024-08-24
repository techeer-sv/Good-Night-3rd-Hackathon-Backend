package com.example.techeertree.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wish extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Confirm isConfirm = Confirm.PENDING;

    @Builder
    protected Wish(String title, String content, Category category, Confirm isConfirm) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.isConfirm = isConfirm == null ? Confirm.PENDING : isConfirm;
    }

}



// 제목, 내용, 카테고리, 등록일, 승인 상태 정보를 포함해야 합니다.
// 카테고리의 종류는 진로, 건강, 인간 관계, 돈, 목표, 학업/성적, 기타 총 7가지입니다.
//is_confirm(소원 승인 상태)값의 초기값을 “보류됨” 으로 설정합니다.
//승인 상태 값 정의 ㅇㅋㅇㅋ
//승인됨 : 소원이 승인된 상태입니다.
//보류됨 : 소원이 아직 검토 중이며 승인되지 않은 상태입니다.
//거절됨 : 소원이 거절된 상태입니다.
//제목, 내용, 카테고리, 등록일 값이 하나라도 null일 경우 예외를 처리합니다.(추가기능)