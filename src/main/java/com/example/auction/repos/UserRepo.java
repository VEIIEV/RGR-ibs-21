package com.example.auction.repos;

import com.example.auction.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findUserByLogin(String login);
}
