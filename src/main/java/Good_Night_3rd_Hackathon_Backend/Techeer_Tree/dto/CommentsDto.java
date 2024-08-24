package Good_Night_3rd_Hackathon_Backend.Techeer_Tree.dto;

import Good_Night_3rd_Hackathon_Backend.Techeer_Tree.domain.Wishes;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentsDto {
    private Long wishId;
    private String content;
    private LocalDateTime createdAt;
}
