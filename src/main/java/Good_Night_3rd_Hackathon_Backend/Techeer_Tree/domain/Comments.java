package Good_Night_3rd_Hackathon_Backend.Techeer_Tree.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    private Wishes wish;

    @Column(nullable = false)
    private String content;

    private LocalDateTime createdAt;

    private Boolean isDeleted = false;
}
