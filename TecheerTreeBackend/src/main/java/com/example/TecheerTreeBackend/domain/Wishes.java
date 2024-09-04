package com.example.TecheerTreeBackend.domain;

import com.example.TecheerTreeBackend.dto.WishRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class Wishes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wish_id")
    private Long id;
    private String title;
    private String content;
    @Enumerated(EnumType.STRING)
    private Category category; // 진로, 건강, 인간 관계, 돈, 목표, 학업/성적, 기타
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private WishStatus wishStatus = WishStatus.PENDING; // 초기값 = "보류중"
    private Boolean isDeleted = Boolean.FALSE;

    private Wishes(String title, String content, Category category, Date createAt) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdAt = createAt;
    }

    public static Wishes createWish(WishRequest wishRequest) {
        if (wishRequest.getTitle() == null || wishRequest.getContent() == null || wishRequest.getCategory() == null || wishRequest.getCreateAt() == null) {
            throw new IllegalArgumentException("Title, Content, Category, and createdAt cannot be null.");
        }

        return new Wishes(
                wishRequest.getTitle(),
                wishRequest.getContent(),
                wishRequest.getCategory(),
                wishRequest.getCreateAt()
        );
    }

    public void softDelete() {
        this.isDeleted = Boolean.TRUE;
    }

    public String confirm(WishStatus wishStatus) {
        this.wishStatus = wishStatus;
        return this.wishStatus.getDescription(); // 상태의 설명을 반환
    }

    public Boolean checkConfirm() {
        return this.wishStatus == WishStatus.APPROVED;
    }
}
