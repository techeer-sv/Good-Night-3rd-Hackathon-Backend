package com.example.TecheerTreeBackend.domain;


import com.example.TecheerTreeBackend.dto.CommentForm;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "wish_id")
    private Wish wish;
    private String content;
    private Date created_at;
    private Boolean is_deleted = Boolean.FALSE;

    public Comment(Wish wish, String content, Date createdAt) {
        this.wish = wish;
        this.content = content;
        this.created_at = createdAt;
    }

    public static Comment createComment(Wish wish, CommentForm commentForm) {
        return new Comment(
                wish,
                commentForm.getContent(),
                commentForm.getCreated_at()
        );
    }
}
