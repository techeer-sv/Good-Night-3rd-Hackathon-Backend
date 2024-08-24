package me.kyung.TecheerTree.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column
    private boolean deletedAt = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wish_id")
    private Wish wish;


    public Comment(final String content, final Wish wish) {
        this.content = content;
        this.wish = wish;
    }
}
