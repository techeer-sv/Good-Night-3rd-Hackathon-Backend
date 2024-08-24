package com.techeer.tree.Good_Night_3rd_Hackathon_Backend.controller;

import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.dto.request.WishRequest;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.dto.response.WishResponse;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.entity.Wish;
import com.techeer.tree.Good_Night_3rd_Hackathon_Backend.service.WishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

  @Operation(summary = "Delete a wish", description = "Delete a wish")
  @DeleteMapping("/{id}")
  public void deleteWish(@PathVariable Long id) {
    wishService.deleteWish(id);
  }
}
