package com.example.auction.services.validations;

import com.example.auction.DTO.UserDTO;

import javax.xml.bind.ValidationException;

import static java.util.Objects.isNull;

public class UserVal {
    public boolean valUserDTO(UserDTO userDTO) throws ValidationException {
        if (isNull(userDTO)) {
            throw new ValidationException("Object is null");
        }
        if (isNull(userDTO.getLogin()) || userDTO.getLogin().isEmpty()) {
            throw new ValidationException("Login is Empty");
        }
        if (isNull(userDTO.getPassword()) || userDTO.getPassword().isEmpty()) {
            throw new ValidationException("Password is Empty");
        }
        return true;
    }
}
