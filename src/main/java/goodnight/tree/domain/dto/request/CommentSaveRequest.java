package goodnight.tree.domain.dto.request;

import goodnight.tree.domain.dao.Comment;
import goodnight.tree.domain.dao.Wish;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class CommentSaveRequest {

    @NotBlank(message = "공백일 수 없습니다.")
    private String content;
    @NotNull(message = "공백일 수 없습니다.")
    private Long wishId;


    public Comment toEntity(Wish wish) {
        return new Comment(content, wish);
    }
}
