package com.example.auction.services;

import com.example.auction.DTO.UserDTO;
import com.example.auction.entities.User;
import com.example.auction.repos.UserRepo;
import com.example.auction.services.converts.UserConverter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DefaultUserService implements UserService{

    private final UserRepo userRepo;
    private final UserConverter userConverter;

    @Autowired
    private MailService mailService;

    @Override
    public UserDTO saveUser(UserDTO userDTO) throws ValidationException {
        User user = userConverter.UserDTOToUser(userDTO);
        mailService.sendGreetingMessage(user);
        userRepo.save(user);

        return userConverter.UserToUserDTO(user);
    }

    @Override
    public UserDTO findByLogin(String login) {
        User user = userRepo.findUserByLogin(login);
        if (user != null) {
            return userConverter.UserToUserDTO(user);
        } else return null;
    }

    @Override
    public void deleteUser(int userId) {
        userRepo.deleteById(userId);
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepo.findAll()
                .stream()
                .map(userConverter::UserToUserDTO)
                .collect(Collectors.toList());
    }
}
