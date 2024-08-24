package com.TecheerTree.myproject.domain.entitiy;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private LocalDate createdDate;
    private Long wishId;  // 소원의 ID만 저장
    private boolean deleted_at;
    public Comments() {
    }

    public Comments(String content, LocalDate createdDate, Long wishId, boolean deleted_at) {
        this.content = content;
        this.createdDate = createdDate;
        this.wishId = wishId;
        this.deleted_at = deleted_at;
    }
}
