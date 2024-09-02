package goodnight.tree.domain.dao;

import goodnight.tree.global.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity @Getter
@SQLDelete(sql = "UPDATE wish SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class Wish extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private WishStatus wishStatus = WishStatus.PENDING;

    @OneToMany(mappedBy = "wish", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();


    // Getters and Setters, Constructors

    public enum Category {
        CAREER, HEALTH, RELATIONSHIPS, MONEY, GOALS, STUDIES, OTHER
    }

    public enum WishStatus {
        APPROVED, PENDING, REJECTED
    }

    public Wish(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public void changeStatus(WishStatus newStatus) {
        this.wishStatus = newStatus;
    }
}