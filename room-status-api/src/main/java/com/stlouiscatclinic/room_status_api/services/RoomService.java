package com.stlouiscatclinic.room_status_api.services;

import com.stlouiscatclinic.room_status_api.models.Room;
import com.stlouiscatclinic.room_status_api.repositories.RoomRepository;
import com.stlouiscatclinic.room_status_api.uploadDTOs.RoomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Trevor Gruber
 */

@Service
public class RoomService {
    
    @Autowired
    private RoomRepository roomRepository;
    
    public Room createRoom (RoomDTO roomDTO) {
        return roomRepository.save(new Room(roomDTO.getRoomName()));
    }
}
