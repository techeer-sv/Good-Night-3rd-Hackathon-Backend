package me.kyung.TecheerTree.dto.response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.kyung.TecheerTree.domain.Wish;

@Getter
@Setter
@AllArgsConstructor
public class WishDetailResponse {
    private String title;
    private String content;
    private Wish.Category category;

    public static WishDetailResponse from(Wish wish) {
        return new WishDetailResponse(wish.getTitle(), wish.getContent(), wish.getCategory());
    }
}
