package com.stlouiscatclinic.room_status_api.services;

import com.stlouiscatclinic.room_status_api.dto.download.RoomStatusDownDTO;
import com.stlouiscatclinic.room_status_api.dto.mapper.RoomStatusMapper;
import com.stlouiscatclinic.room_status_api.dto.upload.RoomStatusUpDTO;
import com.stlouiscatclinic.room_status_api.models.RoomStatus;
import com.stlouiscatclinic.room_status_api.repositories.RoomStatusRepository;
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
public class RoomStatusService {
    
    @Autowired
    private RoomStatusRepository roomStatusRepository;
    
    @Autowired
    private RoomStatusMapper roomStatusMapper;
    
    public RoomStatus getRoomStatusById(long id) {
        return roomStatusRepository.findById(id).get();
    }
    
    public RoomStatus createRoomStatus(RoomStatusUpDTO roomStatusUpDTO) {
        return roomStatusRepository.save(new RoomStatus(
                roomStatusUpDTO.getActive(),
                roomStatusUpDTO.getStatusName()));
    }
    
    public List<RoomStatusDownDTO> getRoomStatusDTOList () {
        return roomStatusRepository.findAll().stream().map(roomStatusMapper).collect(Collectors.toList());
    }
    
    public RoomStatusDownDTO getRoomStatusDownDTOById(long id) {
        Optional<RoomStatusDownDTO> result =
                roomStatusRepository.findById(id).map(roomStatusMapper);
        if (result.isEmpty()) {
            throw new RuntimeException("Room Status not found");
        }
        return result.get();
    }
    
    public RoomStatus updateRoomStatus(long id, RoomStatusUpDTO roomStatusUpDTO) {
        RoomStatus roomStatus = roomStatusRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Room Status with ID " + id + " not " +
                        "found"));
        roomStatus.setActive(roomStatusUpDTO.getActive());
        roomStatus.setStatusName(roomStatusUpDTO.getStatusName());
        return roomStatusRepository.save(roomStatus);
    }
    
}
