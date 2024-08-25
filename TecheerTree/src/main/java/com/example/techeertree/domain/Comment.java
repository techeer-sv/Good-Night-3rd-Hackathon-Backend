package com.example.techeertree.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Builder
    public Comment(Long id, String comment, Wish wish) {
        this.id = id;
        this.comment = comment;
        this.wish = wish;
    }

    public void setWish(Wish wish){
        this.wish = wish;
    }
}
