package com.example.TecheerTreeBackend.domain;


import com.example.TecheerTreeBackend.dto.CommentForm;
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
    private Date created_at;
    private Boolean is_deleted = Boolean.FALSE;

    public Comments(Wishes wishes, String content, Date createdAt) {
        this.wishes = wishes;
        this.content = content;
        this.created_at = createdAt;
    }

    public static Comments createComment(Wishes wishes, CommentForm commentForm) {
        return new Comments(
                wishes,
                commentForm.getContent(),
                commentForm.getCreated_at()
        );
    }

    public void softDelete() {
        this.is_deleted = Boolean.TRUE;
    }
}
