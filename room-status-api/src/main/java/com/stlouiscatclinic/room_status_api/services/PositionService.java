package com.stlouiscatclinic.room_status_api.services;

import com.stlouiscatclinic.room_status_api.dto.download.PositionDownDTO;
import com.stlouiscatclinic.room_status_api.dto.mapper.PositionMapper;
import com.stlouiscatclinic.room_status_api.dto.upload.PositionUpDTO;
import com.stlouiscatclinic.room_status_api.models.Position;
import com.stlouiscatclinic.room_status_api.repositories.PositionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Trevor Gruber
 */
@Service
public class PositionService {
    
    @Autowired
    private PositionRepository positionRepository;
    
    @Autowired
    private PositionMapper positionMapper;
    
    public Position getPositionById(Integer positionId) {
        return positionRepository.findById((long) positionId).get();
    }
    
    public Position createPosition(PositionUpDTO positionUpDTO) {
        return positionRepository.save(new Position(
                positionUpDTO.getActive(),
                positionUpDTO.getPositionName()));
    }
    
    public List<PositionDownDTO> getPositionDTOList () {
        return positionRepository.findAll().stream().map(positionMapper).collect(Collectors.toList());
    }
    
    public PositionDownDTO getPositionDownDTOById(long id) {
        Optional<PositionDownDTO> result = positionRepository.findById(id).map(positionMapper);
        if (result.isEmpty()){
            throw new RuntimeException("Position not found");
        }
        return result.get();
    }
    
    public Position updatePosition(long id, PositionUpDTO positionUpDTO) {
        Position position = positionRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Service with ID " + id + " not " +
                        "found"));
        position.setActive(positionUpDTO.getActive());
        position.setPositionName(positionUpDTO.getPositionName());
        return positionRepository.save(position);
    }
}
