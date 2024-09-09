package com.techeer.tree.Good_Night_3rd_Hackathon_Backend.controller;

import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.dto.request.WishRequest;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.dto.response.WishResponse;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.entity.Wish;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.service.WishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishes")
@AllArgsConstructor
@Tag(name = "Wish", description = "Create a new wish")
public class WishController {

  private final WishService wishService;

  @Operation(summary = "Create a new wish", description = "Create a new wish")
  @PostMapping("")
  public WishResponse.WishCreateResponse createWish(@RequestBody @Valid WishRequest.WishCreateRequest request) {
    Wish wish = wishService.createWish(request);
    return WishResponse.WishCreateResponse.from(wish);
  }

  @Operation(summary = "Delete a wish", description = "Delete a wish by marking it as deleted (soft delete)")
  @DeleteMapping("/{wish_id}")
  public ResponseEntity<WishResponse.WishDeleteResponse> deleteWish(@PathVariable Long wish_id) {
    try {
      wishService.deleteWish(wish_id);
      WishResponse.WishDeleteResponse response = WishResponse.WishDeleteResponse.success(wish_id);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      WishResponse.WishDeleteResponse response = WishResponse.WishDeleteResponse.failure(wish_id, "소원 삭제 실패: " + e.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
  }

  @Operation(summary = "Approve or reject a wish", description = "Update the confirmation status of a wish")
  @PatchMapping("/{wish_id}")
  public ResponseEntity<WishResponse.WishUpdateResponse> updateWishConfirmation(@PathVariable Long wish_id, @RequestParam boolean is_confirmed) {
    try {
      wishService.updateWishConfirmation(wish_id, is_confirmed);
      WishResponse.WishUpdateResponse response = WishResponse.WishUpdateResponse.success(wish_id);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      WishResponse.WishUpdateResponse response = WishResponse.WishUpdateResponse.failure(wish_id, "소원 수정 실패: " + e.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
  }

  @Operation(summary = "View a wish", description = "View a wish by its ID")
  @GetMapping("/{wish_id}")
  public ResponseEntity<WishResponse.WishReadDetailResponse> getWish(@PathVariable Long wish_id) {
    try {
      Wish wish = wishService.readDetailWish(wish_id);
      WishResponse.WishReadDetailResponse response = WishResponse.WishReadDetailResponse.success(wish);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      WishResponse.WishReadDetailResponse response = WishResponse.WishReadDetailResponse.failure(wish_id, "소원 조회 실패: " + e.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
  }

  @Operation(summary = "Get list of wishes", description = "Retrieve a paginated list of wishes with optional filters for category and confirmation status")
  @GetMapping
  public ResponseEntity<WishResponse.WishReadListWrapperResponse> getAllWishes(
      @RequestParam(required = false) String category,
      @RequestParam(required = false) String is_confirmed) {

    Boolean confirmed = is_confirmed != null ? Boolean.valueOf(is_confirmed) : null;
    List<WishResponse.WishReadListResponse> wishes = wishService.getAllWishes(category, confirmed);

    if (wishes.isEmpty()) {
      WishResponse.WishReadListWrapperResponse response = new WishResponse.WishReadListWrapperResponse(wishes, 400, "소원이 없습니다.");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    WishResponse.WishReadListWrapperResponse response = new WishResponse.WishReadListWrapperResponse(wishes, 200, "소원 목록 조회 성공");
    return ResponseEntity.ok(response);
  }

  @Operation(summary = "Search wishes by title", description = "Search for wishes by title keyword")
  @GetMapping("/search")
  public ResponseEntity<WishResponse.WishSearchResponseWrapper> searchWishesByTitle(@RequestParam String keyword) {
    List<WishResponse.WishSearchResponse> wishes = wishService.searchWishesByTitle(keyword);

    if (wishes.isEmpty()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new WishResponse.WishSearchResponseWrapper(wishes, 400, "검색 결과가 없습니다."));
    }

    WishResponse.WishSearchResponseWrapper response = new WishResponse.WishSearchResponseWrapper(wishes, 200, "소원 검색 성공");
    return ResponseEntity.ok(response);
  }
}