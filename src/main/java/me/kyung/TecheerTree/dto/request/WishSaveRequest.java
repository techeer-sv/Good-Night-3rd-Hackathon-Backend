package me.kyung.TecheerTree.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import me.kyung.TecheerTree.domain.Wish;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class WishSaveRequest {

    @NotBlank(message = "제목은 공백일 수 없습니다.")
    private String title;

    @NotBlank(message = "내용은 공백일 수 없습니다.")
    private String content;

    @NotNull(message = "카테고리는 필수입니다.")
    private Wish.Category category;

    public Wish toEntity() {
        return new Wish(title, content, category);
    }
}
