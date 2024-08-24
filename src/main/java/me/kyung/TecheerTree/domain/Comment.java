package me.kyung.TecheerTree.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Where(clause = "deleted_at = false")
@SQLDelete(sql = "UPDATE comment SET deleted_at = true WHERE id = ?")
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
