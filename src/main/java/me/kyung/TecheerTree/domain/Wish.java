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
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE wish SET deleted = true WHERE id = ?")
@NoArgsConstructor
public class Wish {

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

    @Column(nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status isConfirm = Status.PENDING;

    @Column
    private boolean deletedAt = Boolean.FALSE;

    // Getters and Setters

    public enum Category {
        CAREER, HEALTH, RELATIONSHIP, MONEY, GOAL, ACADEMICS, OTHER
    }

    public enum Status {
        APPROVED, PENDING, REJECTED
    }
}

