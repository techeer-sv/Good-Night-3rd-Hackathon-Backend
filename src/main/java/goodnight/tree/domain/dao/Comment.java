package goodnight.tree.domain.dao;

import goodnight.tree.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@NoArgsConstructor
@Entity @Getter
// 소프트 삭제 구현 delete 메소드 사용 시 실제 삭제가 아닌 소프트 삭제로 구동
@SQLDelete(sql = "UPDATE comment SET deleted_at = NOW() WHERE id = ?")
// 매 조회 시 deleted at이 null인 값만
@Where(clause = "deleted_at IS NULL")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "wish_id", nullable = false)
    private Wish wish;

    private String content;

    public Comment(final String content, final Wish wish) {
        this.content = content;
        this.wish = wish;
    }

}