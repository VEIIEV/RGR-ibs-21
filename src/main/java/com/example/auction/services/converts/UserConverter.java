package com.example.auction.services.converts;

import com.example.auction.DTO.UserDTO;
import com.example.auction.entities.User;
import com.example.auction.services.validations.UserVal;
import org.springframework.stereotype.Component;

import javax.xml.bind.ValidationException;

@Component
public class UserConverter {

    public User UserDTOToUser (UserDTO userDTO) throws ValidationException {
        UserVal userVal = new UserVal();

        if (userVal.valUserDTO(userDTO)) {
            User user = new User();
            user.setId(userDTO.getId());
            user.setEmail(userDTO.getEmail());
            user.setLogin(userDTO.getLogin());
            user.setPassword(userDTO.getPassword());
            user.setIs_adm(userDTO.isIs_adm());

            return user;
        }

        return null;
    }

    public UserDTO UserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setIs_adm(user.isIs_adm());

        return userDTO;
    }
}
