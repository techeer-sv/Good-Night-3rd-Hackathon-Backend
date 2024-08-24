package Good_Night_3rd_Hackathon_Backend.Techeer_Tree.controller;

import Good_Night_3rd_Hackathon_Backend.Techeer_Tree.domain.Wishes;
import Good_Night_3rd_Hackathon_Backend.Techeer_Tree.dto.WishesDto;
import Good_Night_3rd_Hackathon_Backend.Techeer_Tree.service.WishesService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/wishes")
public class WishesController {

    private final WishesService wishesService;

    public WishesController(WishesService wishesService) {
        this.wishesService = wishesService; // WishesController 객체가 생성될 때 WishesService 객체 주입
    }

    @PostMapping
    public Wishes createWish(@RequestBody WishesDto wishDto) {
        return wishesService.createWish(wishDto);
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

}