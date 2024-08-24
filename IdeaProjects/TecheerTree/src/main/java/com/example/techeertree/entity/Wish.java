package com.example.techeertree.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Wish {

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

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApprovalStatus isConfirm = ApprovalStatus.PENDING;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    // Getter와 Setter 메서드
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public ApprovalStatus getIsConfirm() {
        return isConfirm;
    }

    public void setIsConfirm(ApprovalStatus isConfirm) {
        this.isConfirm = isConfirm;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    // Category와 ApprovalStatus 열거형 정의
    public enum Category {
        CAREER, HEALTH, RELATIONSHIP, MONEY, GOAL, STUDY, ETC
    }

    public enum ApprovalStatus {
        APPROVED, PENDING, REJECTED
    }

    // 기본 생성자
    public Wish() {}

    // 추가적인 생성자
    public Wish(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdDate = LocalDateTime.now();
        this.isConfirm = ApprovalStatus.PENDING;
        this.isDeleted = false;
    }
}
