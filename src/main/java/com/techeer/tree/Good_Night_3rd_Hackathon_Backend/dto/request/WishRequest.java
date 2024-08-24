package com.techeer.tree.Good_Night_3rd_Hackathon_Backend.dto.request;

import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.entity.Wish;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WishRequest {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class WishCreateRequest {
        @Schema(description = "소원의 제목", example = "string")
        @NotBlank(message = "공백일 수 없습니다.")
        private String title;

        @Schema(description = "소원의 내용", example = "string")
        @NotBlank(message = "공백일 수 없습니다.")
        private String content;

        @Schema(description = "소원의 카테고리", example = "string")
        @NotBlank(message = "공백일 수 없습니다.")
        private String category;

        public Wish toEntity() {
            return new Wish(title, content, category);
        }
    }

//    @Getter
//    @Setter
//    @NoArgsConstructor
//    public static class WishUpdateRequest {
//        @Schema(description = "새로운 제목", example = "New Wish Title")
//        @NotBlank(message = "공백일 수 없습니다.")
//        private String newTitle;
//
//        @Schema(description = "새로운 내용", example = "New Wish Content")
//        @NotBlank(message = "공백일 수 없습니다.")
//        private String newContent;
//
//        public void updateEntity(Wish wish) {
//            wish.setTitle(newTitle);
//            wish.setContent(newContent);
//        }
//    }

    // 추가적인 요청 DTO를 필요에 따라 이 안에 정의할 수 있습니다.
}