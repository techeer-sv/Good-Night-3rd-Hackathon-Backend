package com.techeer.tree.Good_Night_3rd_Hackathon_Backend.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.entity.Comment;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.entity.Wish;

@Getter
@Setter
@NoArgsConstructor
public class CommentRequest {

  @Getter
  @Setter
  @NoArgsConstructor
  public static class CommentCreateRequest {

    @Schema(description = "댓글 내용", example = "string")
    @NotBlank(message = "공백일 수 없습니다.")
    private String content;

    public Comment toEntity(Wish wish) {
      return new Comment(content, wish);
    }
  }
}