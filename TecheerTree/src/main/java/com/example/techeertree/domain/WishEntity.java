package com.example.techeertree.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Entity(name = "Wish")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WishEntity extends BaseEntity{

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

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL )
    private List<Comment> comments = new ArrayList<>();

    @Builder
    protected WishEntity(String title, String content, Category category, Confirm isConfirm, List<Comment> comments) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.isConfirm = isConfirm == null ? Confirm.PENDING : isConfirm;
        this.isDeleted = false;
        this.comments = comments;

    }

    public void setDeleted(){
        this.isDeleted = true;
        this.setDeletedAt(LocalDateTime.now());
    }

    public void updateIsConfirm(Confirm isConfirm){
        this.isConfirm = isConfirm;
    }
}
