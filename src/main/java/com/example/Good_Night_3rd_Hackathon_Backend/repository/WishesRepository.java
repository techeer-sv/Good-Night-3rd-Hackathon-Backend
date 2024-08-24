package com.example.Good_Night_3rd_Hackathon_Backend.repository;

import com.example.Good_Night_3rd_Hackathon_Backend.domain.ConfirmStatus;
import com.example.Good_Night_3rd_Hackathon_Backend.domain.Wishes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishesRepository extends JpaRepository<Wishes, Long> {
    Optional<Wishes> findByIdAndIsDeletedFalseAndIsConfirmed(Long id, ConfirmStatus is_confirmed);
}
