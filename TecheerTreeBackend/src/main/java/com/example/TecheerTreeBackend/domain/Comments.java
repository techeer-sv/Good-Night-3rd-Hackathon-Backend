package com.example.TecheerTreeBackend.domain;


import com.example.TecheerTreeBackend.dto.CommentRequest;
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
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "wish_id")
    private Wishes wishes;

    private String content;

    @CreatedDate
    private Date createdAt;

    private Boolean isDeleted = Boolean.FALSE;

    private Comments(Wishes wishes, String content) {
        this.wishes = wishes;
        this.content = content;
    }

    public static Comments from(Wishes wishes, CommentRequest commentRequest) {
        return new Comments(
                wishes,
                commentRequest.getContent()
        );
    }

    public void softDelete() {
        this.isDeleted = Boolean.TRUE;
    }
}
