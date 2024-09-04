package com.example.TecheerTreeBackend.domain;


import com.example.TecheerTreeBackend.dto.CommentRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "wish_id")
    private Wishes wishes;
    private String content;
    private Date createdAt;
    private Boolean isDeleted = Boolean.FALSE;

    private Comments(Wishes wishes, String content, Date createdAt) {
        this.wishes = wishes;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static Comments from(Wishes wishes, CommentRequest commentRequest) {
        return new Comments(
                wishes,
                commentRequest.getContent(),
                commentRequest.getCreatedAt()
        );
    }

    public void softDelete() {
        this.isDeleted = Boolean.TRUE;
    }
}
