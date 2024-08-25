package com.example.techeertree.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wishId")
    private Wish wish;

    @ColumnDefault("false")
    private boolean isDeleted = false;

    @Builder
    public Comment(Long id, String comment, Wish wish) {
        this.id = id;
        this.comment = comment;
        this.wish = wish;
        this.isDeleted = false;
    }

    public void matchWish(Wish wish){
        this.wish = wish;
    }

    public void setDeleted(){
        this.isDeleted = true;
        this.setDeletedAt(LocalDateTime.now());
    }
}
