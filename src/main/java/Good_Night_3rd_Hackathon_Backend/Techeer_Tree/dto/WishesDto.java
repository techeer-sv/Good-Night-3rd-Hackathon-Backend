package Good_Night_3rd_Hackathon_Backend.Techeer_Tree.dto;

import Good_Night_3rd_Hackathon_Backend.Techeer_Tree.domain.Wishes.Category;
import Good_Night_3rd_Hackathon_Backend.Techeer_Tree.domain.Wishes.WishesStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class WishesDto {
    private String title;
    private String content;
    private Category category;
    private LocalDate createdAt;
    private WishesStatus isConfirm;
}