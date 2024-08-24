package com.example.TecheerTreeBackend.domain;

import com.example.TecheerTreeBackend.dto.WishConfirmForm;
import com.example.TecheerTreeBackend.dto.WishForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wish_id")
    private Long id;
    private String title;
    private String content;
    private String category; // 진로, 건강, 인간 관계, 돈, 목표, 학업/성적, 기타
    private Date created_at;
    @Enumerated(EnumType.STRING)
    private WishStatus is_confirm = WishStatus.PENDING; // 초기값 = "보류중"
    private Boolean is_deleted = Boolean.FALSE;

    public Wish(String title, String content, String category, Date createAt) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.created_at = createAt;
    }

    public static Wish createWish(WishForm wishForm) {
        if (wishForm.getTitle() == null || wishForm.getContent() == null || wishForm.getCategory() == null || wishForm.getCreate_at() == null) {
            throw new IllegalArgumentException("Title, Content, Category, and Created_at cannot be null.");
        }
        return new Wish(
                wishForm.getTitle(),
                wishForm.getContent(),
                wishForm.getCategory(),
                wishForm.getCreate_at()
        );
    }

    public void softDelete() {
        this.is_deleted = Boolean.TRUE;
    }

    public String confirm(WishConfirmForm wishConfirmForm) {
        this.is_confirm = wishConfirmForm.getConfirm();
        return this.is_confirm.getDescription(); // 상태의 설명을 반환
    }

    public Boolean checkConfirm() {
        return this.is_confirm == WishStatus.APPROVED;
    }
}
