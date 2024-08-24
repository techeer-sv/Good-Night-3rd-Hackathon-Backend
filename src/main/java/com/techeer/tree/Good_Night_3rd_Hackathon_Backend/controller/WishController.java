package com.techeer.tree.Good_Night_3rd_Hackathon_Backend.controller;

import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.dto.request.WishRequest;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.dto.response.WishResponse;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.entity.Wish;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.service.WishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
  public WishResponse createWish(@RequestBody @Valid WishRequest.WishCreateRequest request) {
    Wish wish = wishService.createWish(request);
    return WishResponse.from(wish);
  }

  @Operation(summary = "Delete a wish", description = "Delete a wish by marking it as deleted (soft delete)")
  @DeleteMapping("/{id}")
  public ResponseEntity<WishResponse.WishDeleteResponse> deleteWish(@PathVariable Long id) {
    try {
      wishService.deleteWish(id);
      WishResponse.WishDeleteResponse response = WishResponse.WishDeleteResponse.success(id);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      WishResponse.WishDeleteResponse response = WishResponse.WishDeleteResponse.failure(id, "소원 삭제 실패: " + e.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
  }

  @Operation(summary = "Approve or reject a wish", description = "Update the confirmation status of a wish")
  @PatchMapping("/{id}")
  public ResponseEntity<WishResponse.WishUpdateResponse> updateWishConfirmation(@PathVariable Long id, @RequestParam boolean is_confirmed) {
    try {
      wishService.updateWishConfirmation(id, is_confirmed);
      WishResponse.WishUpdateResponse response = WishResponse.WishUpdateResponse.success(id);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }
}
