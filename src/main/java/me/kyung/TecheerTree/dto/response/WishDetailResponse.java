package me.kyung.TecheerTree.dto.response;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.kyung.TecheerTree.domain.Wish;

@Getter
@Setter
public class WishDetailResponse {
    private String title;
    private String content;
    private Wish.Category category;

    public WishDetailResponse(String title, String content, Wish.Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }


    // 정적 팩토리 메서드
    public static WishDetailResponse from(Wish wish) {
        return new WishDetailResponse(wish.getTitle(), wish.getContent(), wish.getCategory());
    }
}
