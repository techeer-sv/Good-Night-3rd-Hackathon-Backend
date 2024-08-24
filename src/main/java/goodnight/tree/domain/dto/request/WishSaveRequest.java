package goodnight.tree.domain.dto.request;

import goodnight.tree.domain.dao.Wish;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class WishSaveRequest {

    @NotBlank(message = "공백일 수 없습니다.")
    private String title;

    @NotBlank(message = "공백일 수 없습니다.")
    private String content;

    @NotNull(message = "카테고리는 필수 항목입니다.")
    private Wish.Category category;

    public Wish toEntity() {
        return new Wish(title, content, category);
    }
}
