package com.example.TecheerTreeBackend.domain;

import com.example.TecheerTreeBackend.dto.WishRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Wishes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wish_id")
    private Long id;

    private String title;
    private String content;

    @Enumerated(EnumType.STRING)
    private Category category; // 진로, 건강, 인간 관계, 돈, 목표, 학업/성적, 기타

    @CreatedDate
    private Date createdAt;

    @Enumerated(EnumType.STRING)
    private WishStatus wishStatus = WishStatus.PENDING; // 초기값 = "보류중"

    private Boolean isDeleted = Boolean.FALSE;

    private Wishes(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public static Wishes from(WishRequest wishRequest) {
        if (wishRequest.getTitle() == null || wishRequest.getContent() == null || wishRequest.getCategory() == null) {
            throw new IllegalArgumentException("Title, Content, Category, and createdAt cannot be null.");
        }

        return new Wishes(
                wishRequest.getTitle(),
                wishRequest.getContent(),
                wishRequest.getCategory()
        );
    }

    public void softDelete() {
        this.isDeleted = Boolean.TRUE;
    }

    public void confirm(WishStatus wishStatus) {
        this.wishStatus = wishStatus;
    }

    public Boolean checkConfirm() {
        return this.wishStatus == WishStatus.APPROVED;
    }
}
