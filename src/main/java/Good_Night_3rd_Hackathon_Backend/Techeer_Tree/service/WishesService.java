package Good_Night_3rd_Hackathon_Backend.Techeer_Tree.service;

import Good_Night_3rd_Hackathon_Backend.Techeer_Tree.repository.WishesRepository;
import org.springframework.stereotype.Service;

@Service
public class WishesService {

    private final WishesRepository wishesRepository;

    public WishesService(WishesRepository wishesRepository) {
        this.wishesRepository = wishesRepository;
    }
}