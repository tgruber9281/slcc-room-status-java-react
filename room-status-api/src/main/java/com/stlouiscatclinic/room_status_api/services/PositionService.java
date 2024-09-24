package com.stlouiscatclinic.room_status_api.services;

import com.stlouiscatclinic.room_status_api.models.Position;
import com.stlouiscatclinic.room_status_api.models.Room;
import com.stlouiscatclinic.room_status_api.repositories.PositionRepository;
import com.stlouiscatclinic.room_status_api.uploadDTOs.PositionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Trevor Gruber
 */
@Service
public class PositionService {
    
    @Autowired
    private PositionRepository positionRepository;
    
    public Position getPositionById(Integer positionId) {
        return positionRepository.getReferenceById((long) positionId);
    }
    
    public Position createPosition(PositionDTO positionDTO) {
        return positionRepository.save(new Position(positionDTO.getPositionName()));
    }
}
