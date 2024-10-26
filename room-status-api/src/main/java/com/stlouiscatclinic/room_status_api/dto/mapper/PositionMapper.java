package com.stlouiscatclinic.room_status_api.dto.mapper;

import com.stlouiscatclinic.room_status_api.dto.download.PositionDownDTO;
import com.stlouiscatclinic.room_status_api.models.Position;
import com.stlouiscatclinic.room_status_api.models.Staff;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Created by Trevor Gruber
 */

@Service
public class PositionMapper implements Function<Position, PositionDownDTO> {
    
    @Override
    public PositionDownDTO apply(Position position) {
        return new PositionDownDTO(
                position.getId(),
                position.isActive(),
                position.getPositionName(),
                position.getStaffPositionList().stream().map(Staff::getId).toList()
        );
    }
}
