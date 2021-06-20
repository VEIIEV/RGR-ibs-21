package com.example.auction.services;

import com.example.auction.DTO.LotDTO;
import com.example.auction.entities.Lot;
import com.example.auction.repos.LotRepo;
import com.example.auction.services.converts.LotConverter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DefaultLotService implements LotService {
    private final LotRepo lotRepo;
    private final LotConverter lotConverter;

    @Autowired
    private MailService mailService;

    @Override
    public LotDTO saveLot(LotDTO lotDTO) throws ValidationException {
        Lot lot = lotConverter.LotDTOToLot(lotDTO);
        lotRepo.save(lot);

        return lotConverter.LotToLotDTO(lot);
    }

    @Override
    public LotDTO findByName(String name) {
        Lot lot = lotRepo.findUserByName(name);

        if (lot != null) {
            return lotConverter.LotToLotDTO(lot);
        } else return null;
    }

    @Override
    public List<LotDTO> findAllByUserId(int id) {
        return lotRepo.findAllByUserId(id)
                .stream()
                .map(lotConverter::LotToLotDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteLot(int lotId) {
        lotRepo.deleteById(lotId);
    }

    @Override
    public List<LotDTO> findAll() {
        return lotRepo.findAll()
                .stream()
                .map(lotConverter::LotToLotDTO)
                .collect(Collectors.toList());
    }
}
