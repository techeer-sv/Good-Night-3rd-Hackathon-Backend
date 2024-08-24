package Good_Night_3rd_Hackathon_Backend.Techeer_Tree.controller;

import Good_Night_3rd_Hackathon_Backend.Techeer_Tree.domain.Wishes;
import Good_Night_3rd_Hackathon_Backend.Techeer_Tree.dto.WishesDto;
import Good_Night_3rd_Hackathon_Backend.Techeer_Tree.service.WishesService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.List;

@RestController
@RequestMapping("/wishes")
public class WishesController {

    private final WishesService wishesService;

    public WishesController(WishesService wishesService) {
        this.wishesService = wishesService; // WishesController 객체가 생성될 때 WishesService 객체 주입
    }

    @PostMapping
    public ResponseEntity<?> createWish(@RequestBody WishesDto wishDto) {
        try {
            Wishes createdWish = wishesService.createWish(wishDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdWish);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWish(@PathVariable Long id) {
        try {
            wishesService.deleteWish(id);
            return ResponseEntity.ok("소원이 삭제되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // 해당 소원이 존재하지 않습니다.
        }
    }

    @PatchMapping("/{id}/approve")
    public ResponseEntity<String> approveWish(@PathVariable Long id, @RequestBody Wishes.WishesStatus status) {
        try {
            wishesService.approveWish(id, status);
            if (status == Wishes.WishesStatus.APPROVED) {
                return ResponseEntity.ok("소원이 승인되었습니다.");
            } else {
                return ResponseEntity.ok("소원이 거절되었습니다.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // 해당 소원이 존재하지 않습니다.
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getWish(@PathVariable Long id) {
        try {
            Wishes wish = wishesService.getWish(id);
            return ResponseEntity.ok(wish);
        } catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.equals("삭제된 소원입니다.") || errorMessage.equals("승인되지 않은 소원입니다.")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
            } else if (errorMessage.equals("해당 소원이 존재하지 않습니다.")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
            }
        }
    }

    @GetMapping
    public Page<Wishes> getWishes(
            @RequestParam(required = false) Wishes.WishesStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return wishesService.getWishList(status, pageable);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Wishes>> searchWishes(@RequestParam String keyword, @RequestParam Wishes.Category category) {
        List<Wishes> results = wishesService.searchWishes(keyword, category);
        if (results.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 검색 결과가 없을 때 204 반환
        }
        return new ResponseEntity<>(results, HttpStatus.OK); // 성공 시 200 반환
    }
}