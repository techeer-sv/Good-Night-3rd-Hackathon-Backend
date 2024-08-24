package goodnight.tree.domain.dto.response;

import goodnight.tree.domain.dao.Comment;
import goodnight.tree.domain.dao.Wish;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentResponse {
    private Long id;
    private String content;

    public static CommentResponse from(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getContent());
    }
}
