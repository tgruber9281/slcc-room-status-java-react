package com.stlouiscatclinic.room_status_api.services;

import com.stlouiscatclinic.room_status_api.models.RoomStatus;
import com.stlouiscatclinic.room_status_api.repositories.RoomStatusRepository;
import com.stlouiscatclinic.room_status_api.uploadDTOs.RoomStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Trevor Gruber
 */
@Service
public class RoomStatusService {
    
    @Autowired
    private RoomStatusRepository roomStatusRepository;
    
    public RoomStatus createRoomStatus(RoomStatusDTO roomStatusDTO) {
        return roomStatusRepository.save(new RoomStatus(roomStatusDTO.getStatusName()));
    }
}
