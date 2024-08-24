package com.example.Good_Night_3rd_Hackathon_Backend.config;

import com.example.Good_Night_3rd_Hackathon_Backend.repository.CommentsRepository;
import com.example.Good_Night_3rd_Hackathon_Backend.repository.WishesRepository;
import com.example.Good_Night_3rd_Hackathon_Backend.service.CommentsService;
import com.example.Good_Night_3rd_Hackathon_Backend.service.WishesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final WishesRepository wishesRepository;
    private final CommentsRepository commentsRepository;

    @Autowired
    public SpringConfig(WishesRepository wishesRepository, CommentsRepository commentsRepository) {
        this.wishesRepository = wishesRepository;
        this.commentsRepository = commentsRepository;
    }

    @Bean
    public WishesService wishesService() {
        return new WishesService(wishesRepository);
    }

    @Bean
    public CommentsService commentsService() {
        return new CommentsService(commentsRepository, wishesRepository);
    }
}
