package Good_Night_3rd_Hackathon_Backend.Techeer_Tree.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Wishes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id 값 자동 생성
    private Long id;

    @Column(nullable = false) // null 값 허용 X
    private String title;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING) // 열거형 -> DB에 문자열로 저장
    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Enumerated(EnumType.STRING)
    private WishesStatus isConfirm = WishesStatus.PENDING;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    public enum Category {
        DREAM, HEALTH, RELATIONSHIP, MONEY, GOAL, STUDY, ETC
    }

    public enum WishesStatus {
        APPROVED, PENDING, REJECTED
    }
}