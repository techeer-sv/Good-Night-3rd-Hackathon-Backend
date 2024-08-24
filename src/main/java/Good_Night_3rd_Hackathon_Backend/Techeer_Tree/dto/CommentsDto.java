package Good_Night_3rd_Hackathon_Backend.Techeer_Tree.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentsDto {
    private String content;
    private LocalDateTime createdAt;
}
