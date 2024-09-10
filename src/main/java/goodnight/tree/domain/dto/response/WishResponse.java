package goodnight.tree.domain.dto.response;

import goodnight.tree.domain.dao.Wish;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class WishResponse {
    private Long id;
    private String title;
    private String content;

    public static WishResponse from(Wish wish) {
        return new WishResponse(
                wish.getId(),
                wish.getTitle(),
                wish.getCategory().name()
        );
    }
}
