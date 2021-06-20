package com.example.auction.controllers;

import com.example.auction.DTO.LotDTO;
import com.example.auction.DTO.UserDTO;
import com.example.auction.entities.User;
import com.example.auction.repos.LotRepo;
import com.example.auction.repos.UserRepo;
import com.example.auction.services.LotService;
import com.example.auction.services.MailService;
import com.example.auction.services.converts.UserConverter;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@Log
@RestController
@AllArgsConstructor
@RequestMapping("/lots")
public class LotController {
    private final LotService lotService;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private LotRepo lotRepo;

    @Autowired
    private UserConverter userDTO;

    @Autowired
    private UserConverter UserDTOToUser;

    @PostMapping("/save")
    public LotDTO saveLot(@RequestBody LotDTO lotDTO) throws ValidationException {
        lotService.saveLot(lotDTO);

        return lotDTO;
    }

    @GetMapping("/findAll")
    public List<LotDTO> findAll() {
        return lotService.findAll();
    }

    @GetMapping("/findAllByUserId/{id}")
    public List<LotDTO> findAllByUserId(@PathVariable Integer id) {
        return lotService.findAllByUserId(id);
    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable Integer id) {
        User u = userRepo.findById(lotRepo.findById(id).get().getUserId()).get();
        mailService.sendBuyInformation(u,lotRepo.findById(id).get());
        lotService.deleteLot(id);
    }
}
