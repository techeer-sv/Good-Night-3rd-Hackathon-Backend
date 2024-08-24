package com.example.Good_Night_3rd_Hackathon_Backend.config;

import com.example.Good_Night_3rd_Hackathon_Backend.repository.WishesRepository;
import com.example.Good_Night_3rd_Hackathon_Backend.service.WishesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final WishesRepository wishesRepository;

    @Autowired
    public SpringConfig(WishesRepository wishesRepository) {
        this.wishesRepository = wishesRepository;
    }

    @Bean
    public WishesService wishesService() {
        return new WishesService(wishesRepository);
    }
}
