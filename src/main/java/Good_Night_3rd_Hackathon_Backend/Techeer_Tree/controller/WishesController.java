package Good_Night_3rd_Hackathon_Backend.Techeer_Tree.controller;

import Good_Night_3rd_Hackathon_Backend.Techeer_Tree.service.WishesService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishes")
public class WishesController {

    private final WishesService wishesService;

    public WishesController(WishesService wishesService) {
        this.wishesService = wishesService; // WishesController 객체가 생성될 때 WishesService 객체 주입
    }
}