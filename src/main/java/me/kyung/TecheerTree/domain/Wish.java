package me.kyung.TecheerTree.domain;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Where(clause = "deleted_at = false")
@SQLDelete(sql = "UPDATE wish SET deleted_at = true WHERE id = ?")
@NoArgsConstructor
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private Category category;

    private LocalDateTime createdDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private Status isConfirm = Status.PENDING;

    private boolean deletedAt = Boolean.FALSE;

    public Wish(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }
    // Getters and Setters

    public enum Category {
        CAREER, HEALTH, RELATIONSHIP, MONEY, GOAL, ACADEMICS, OTHER
    }
    @Getter
    public enum Status {
        APPROVED("승인됨"),
        PENDING("보류됨"),
        REJECTED("거절됨");

        private final String description;

        Status(String description) {
            this.description = description;
        }

    }
}


