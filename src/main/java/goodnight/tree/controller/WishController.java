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
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Void> saveWishes(@RequestBody WishSaveRequest request){
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


}
