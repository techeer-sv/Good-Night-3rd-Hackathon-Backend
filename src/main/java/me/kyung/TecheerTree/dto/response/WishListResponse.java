package me.kyung.TecheerTree.dto.response;
import lombok.Getter;
import lombok.Setter;
import me.kyung.TecheerTree.domain.Wish;

import java.time.LocalDateTime;

@Getter
@Setter
public class WishListResponse {
    private String title;
    private Wish.Category category;
    private LocalDateTime createDate;

    public WishListResponse(String title, LocalDateTime createDate, Wish.Category category) {
        this.title = title;
        this.createDate = createDate;
        this.category = category;
    }


    // 정적 팩토리 메서드
    public static WishListResponse from(Wish wish) {
        return new WishListResponse(wish.getTitle(), wish.getCreatedDate(), wish.getCategory());
    }
}
