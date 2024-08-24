package com.techeer.tree.Good_Night_3rd_Hackathon_Backend.dto.response;

import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.entity.Wish;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WishResponse {
  private Long id;
  private String title;
  private String content;
  private String category;

  public static WishResponse from(Wish wish) {
    return new WishResponse(wish.getId(), wish.getTitle(), wish.getContent(), wish.getCategory());
  }
}
