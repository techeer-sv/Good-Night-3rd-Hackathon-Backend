package me.kyung.TecheerTree.dto.response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.kyung.TecheerTree.domain.Wish;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class WishListResponse {
    private Long id;
    private String title;
    private Wish.Category category;
    private LocalDateTime createDate;

    // 정적 팩토리 메서드
    public static WishListResponse from(Wish wish) {
        return new WishListResponse(wish.getId(),wish.getTitle(), wish.getCategory(), wish.getCreatedDate());
    }
}
