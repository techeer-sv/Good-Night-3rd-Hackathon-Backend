package com.example.techeertree.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;


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

    @ColumnDefault("false")
    private boolean isDeleted = false;

    @Builder
    protected Wish(String title, String content, Category category, Confirm isConfirm) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.isConfirm = isConfirm == null ? Confirm.PENDING : isConfirm;
        this.isDeleted = false;
    }

    public void setDeleted(){
        this.isDeleted = true;
        this.setDeletedAt(LocalDateTime.now());
    }

}
