package com.example.Good_Night_3rd_Hackathon_Backend.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
public class Wishes extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String category;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConfirmStatus isConfirmed = ConfirmStatus.PENDING;;

    @Builder
    public Wishes(String title, String content, String category, ConfirmStatus isConfirmed) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.isConfirmed = isConfirmed;
    }
}
