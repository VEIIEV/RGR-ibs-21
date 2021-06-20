package com.example.auction.services;

import com.example.auction.DTO.LotDTO;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface LotService {
    LotDTO saveLot(LotDTO lotDTO) throws ValidationException;
    LotDTO findByName(String name);
    List<LotDTO> findAllByUserId(int id);
    void deleteLot(int lotId);
    List<LotDTO> findAll();
}

