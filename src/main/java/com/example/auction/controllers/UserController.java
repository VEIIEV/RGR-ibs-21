package com.example.auction.controllers;

import com.example.auction.DTO.UserDTO;
import com.example.auction.services.MailService;
import com.example.auction.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@Log
@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/save")
    public UserDTO saveUser(@RequestBody UserDTO userDTO) throws ValidationException {
        return userService.saveUser(userDTO);
    }

    @GetMapping("/findAll")
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    @GetMapping("/findByLogin")
    public UserDTO findByLogin(@RequestParam String login) {
        return userService.findByLogin(login);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
