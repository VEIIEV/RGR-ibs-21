package com.example.auction.repos;

import com.example.auction.entities.Lot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LotRepo extends JpaRepository<Lot, Integer> {
    Lot findUserByName(String name);
    List<Lot> findAllByUserId(int user_id);
}
