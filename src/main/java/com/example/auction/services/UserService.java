package com.example.auction.services;

import com.example.auction.DTO.UserDTO;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface UserService {
    UserDTO saveUser(UserDTO userDTO) throws ValidationException;
    UserDTO findByLogin(String login);
    void deleteUser(int userId);
    List<UserDTO> findAll();
}
