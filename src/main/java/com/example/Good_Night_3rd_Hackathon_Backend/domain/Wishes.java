package com.example.Good_Night_3rd_Hackathon_Backend.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
public class Wishes {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;
    private String content;
    private String category;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConfirmStatus is_confirmed = ConfirmStatus.PENDING;;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created_at;

    @Column(nullable = false)
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updated_at;

    @Column(nullable = false)
    private boolean is_deleted;

    @Builder
    public Wishes(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.is_confirmed = ConfirmStatus.PENDING;
        this.is_deleted = false;
    }

}
