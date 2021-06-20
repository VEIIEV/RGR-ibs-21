package com.example.auction.services.converts;

import com.example.auction.DTO.LotDTO;
import com.example.auction.entities.Lot;
import org.springframework.stereotype.Component;

@Component
public class LotConverter {

    public Lot LotDTOToLot(LotDTO lotDTO) {
        Lot lot = new Lot();

        lot.setId(lotDTO.getId());
        lot.setName(lotDTO.getName());
        lot.setCost(lotDTO.getCost());
        lot.setDescription(lotDTO.getDescription());
        lot.setUserId(lotDTO.getUser_id());

        return lot;
    }

    public LotDTO LotToLotDTO(Lot lot) {
        LotDTO lotDTO = new LotDTO();

        lotDTO.setId(lot.getId());
        lotDTO.setDescription(lot.getDescription());
        lotDTO.setCost(lot.getCost());
        lotDTO.setName(lot.getName());
        lotDTO.setUser_id(lot.getUserId());

        return lotDTO;
    }
}
