package com.techeer.tree.Good_Night_3rd_Hackathon_Backend.dto.response;

import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
public class CommentResponse {
  private Long id;
  private String content;

  public static CommentResponse from(Comment comment) {
    return new CommentResponse(comment.getId(), comment.getContent());
  }

  @Getter
  @AllArgsConstructor
  public static class CommentCreateResponse {
    private Long id;
    private String content;
    private int status;
    private String message;

    public static CommentCreateResponse success(Comment comment) {
      return new CommentCreateResponse(comment.getId(), comment.getContent(), 201, "댓글 생성 성공");
    }

    public static CommentCreateResponse failure(Comment comment, String message) {
      return new CommentCreateResponse(comment.getId(), comment.getContent(), 400, message); // 예: 400 Bad Request
    }
  }

  @Getter
  @AllArgsConstructor
  public static class CommentDeleteResponse {
    private Long id;
    private int status;
    private String message;

    public static CommentDeleteResponse success(Long id) {
      return new CommentDeleteResponse(id, 200, "댓글 삭제 성공");
    }

    public static CommentDeleteResponse failure(Long id, String message) {
      return new CommentDeleteResponse(id, 400, message); // 예: 400 Bad Request
    }
  }

  @Getter
  @AllArgsConstructor
  public static class CommentListResponse {
    private Long id;
    private String content;
    private Long wishId;

    public static CommentListResponse from(Comment comment) {
      return new CommentListResponse(comment.getId(), comment.getContent(), comment.getWish().getId());
    }
  }

  @Getter
  @AllArgsConstructor
  public static class CommentListResponseWrapper {
    private List<CommentListResponse> comments;
    private int status;
    private String message;
  }
}
