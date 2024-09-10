package goodnight.tree.controller;

import goodnight.tree.domain.dao.Wish;
import goodnight.tree.domain.dto.request.WishSaveRequest;
import goodnight.tree.domain.dto.request.WishUpdateRequest;
import goodnight.tree.domain.dto.response.WishDetailResponse;
import goodnight.tree.domain.dto.response.WishResponse;
import goodnight.tree.service.WishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/wishes")
public class WishController {

    private final WishService wishService;

    //  소원 등록
    // request 제목, 내용, 카테고리, 등록일, 승인 상태 정보
    @PostMapping
    public ResponseEntity<Void> saveWishes(@Valid @RequestBody WishSaveRequest request){
        wishService.saveWishes(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    // 소원 승인 / 거절
    @PatchMapping("/{wishId}")
    public ResponseEntity<Void> updateWish(@RequestBody WishUpdateRequest request,
                                           @PathVariable Long wishId){
        wishService.updateWish(request, wishId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 소원 삭제
    @DeleteMapping("/{wishId}")
    public ResponseEntity<Void> deleteWish(@PathVariable Long wishId){
        wishService.deleteWish(wishId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    // 소원 단일 조회
    @GetMapping("/{wishId}")
    public WishDetailResponse getWish(@PathVariable Long wishId){
        WishDetailResponse response = wishService.findWish(wishId);
        return response;
    }

    // 소원 목록 조회
    @GetMapping
    public List<WishResponse> getWishList(@PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                                          @RequestParam(value = "status", defaultValue = "PENDING") Wish.WishStatus status) {
        return wishService.findWishList(pageable, status);
    }

    @GetMapping("/search")
    public List<WishResponse> searchWishes(@RequestParam(required = false) Wish.Category category,
                                   @RequestParam String keyword,
                                   @RequestParam(required = false) Wish.WishStatus status) {
        return wishService.searchWishes(category, keyword, status);
    }

}
