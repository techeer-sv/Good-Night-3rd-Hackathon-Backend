package goodnight.tree.domain.dto.request;

import goodnight.tree.domain.dao.Wish;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class WishUpdateRequest {
    @NotNull(message = "공백일 수 없습니다.")
    private Wish.WishStatus status;
}
