package me.kyung.TecheerTree.dto.request;

import lombok.Getter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import me.kyung.TecheerTree.domain.Comment;
import me.kyung.TecheerTree.domain.Wish;

import java.time.LocalDateTime;

@Getter
public class CommentSaveRequest {
    @NotBlank(message = "내용은 공백일 수 없습니다.")
    private String content;

    @NotNull(message = "소원 ID는 필수입니다.")
    private Long wishId;

    @NotNull
    private final LocalDateTime createDate=LocalDateTime.now();

    public Comment toEntity(Wish wish) {
        return new Comment(content, wish);
    }
}