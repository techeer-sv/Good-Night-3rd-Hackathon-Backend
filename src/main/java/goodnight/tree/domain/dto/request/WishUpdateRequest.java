package goodnight.tree.domain.dto.request;

import goodnight.tree.domain.dao.Wish;
import lombok.Getter;

@Getter
public class WishUpdateRequest {
    private Wish.WishStatus status;
}
