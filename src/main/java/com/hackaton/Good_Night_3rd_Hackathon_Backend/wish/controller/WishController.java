package com.hackaton.Good_Night_3rd_Hackathon_Backend.wish.controller;

import com.hackaton.Good_Night_3rd_Hackathon_Backend.wish.dao.WishDao;
import com.hackaton.Good_Night_3rd_Hackathon_Backend.wish.dto.ConfirmYN;
import com.hackaton.Good_Night_3rd_Hackathon_Backend.wish.entity.Wish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/wish")
public class WishController {

    private final WishDao wishDao;

    @Autowired //의존성 주입.
    public WishController(WishDao wishDao) {
        this.wishDao = wishDao;
    }

    @PostMapping("/create")
    public void createWish(@RequestBody Wish wish) {
        wishDao.createWish(wish);
    }

    @GetMapping("/{id}")
    public String getWish(@PathVariable("id") Long id) {
        Wish wish = wishDao.getWish(id);
        return wish.getTitle();
    }

    // 전체 Wish 리스트를 조회하는 엔드포인트
    @GetMapping("/list") // 소원리스트 쭉 뽑기
    public List<Wish> getWishList() {
        return wishDao.getWishList();
    }
    @DeleteMapping("/delete/{id}")
    public void deleteWish(@PathVariable("id") Long id){
        wishDao.deleteWish(id);
        return;
    }

    @PatchMapping("/approval/{id}")
    public void approvalWish(@PathVariable("id") Long id, @RequestBody ConfirmYN request)
    {

        wishDao.confirmWish(id, request.getConfirm());
    };
}