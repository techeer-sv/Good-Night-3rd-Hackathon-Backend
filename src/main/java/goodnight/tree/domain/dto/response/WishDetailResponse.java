package goodnight.tree.domain.dto.response;

import goodnight.tree.domain.dao.Wish;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class WishDetailResponse {
    private String title;
    private String content;

    private String category;

    public static WishDetailResponse from(Wish wish) {
        return new WishDetailResponse(wish.getTitle()
                , wish.getContent()
                , wish.getCategory().name());
    }

}
