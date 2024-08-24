package me.kyung.TecheerTree.repository;

import me.kyung.TecheerTree.domain.Wish;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WishRepository extends JpaRepository<Wish, Long> {

}