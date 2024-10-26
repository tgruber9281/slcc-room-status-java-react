package com.stlouiscatclinic.room_status_api.services;

import com.stlouiscatclinic.room_status_api.dto.download.RoomDownDTO;
import com.stlouiscatclinic.room_status_api.dto.mapper.RoomMapper;
import com.stlouiscatclinic.room_status_api.dto.upload.RoomUpDTO;
import com.stlouiscatclinic.room_status_api.models.Room;
import com.stlouiscatclinic.room_status_api.repositories.RoomRepository;
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
public class RoomService {
    
    @Autowired
    private RoomRepository roomRepository;
    
    @Autowired
    private RoomMapper roomMapper;
    
    @Autowired
    private RoomStatusService roomStatusService;
    
    @Autowired
    private StaffService staffService;
    
    public Room createRoom(RoomUpDTO roomUpDTO) {
        return roomRepository.save(new Room(
                roomUpDTO.getActive(),
                roomUpDTO.getRoomName()));
    }
    
    public List<RoomDownDTO> getRoomDTOList() {
        return roomRepository.findAll().stream().map(roomMapper).collect(Collectors.toList());
    }
    
    public RoomDownDTO getRoomById(long id) {
        Optional<RoomDownDTO> result = roomRepository.findById(id).map(roomMapper);
        if (result.isEmpty()) {
            throw new RuntimeException("Room not found");
        }
        return result.get();
    }
    
    public RoomDownDTO updateRoom(long id, RoomUpDTO roomUpDTO) {
        Room room = roomRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Room with ID " + id + " not " +
                        "found"));
        if (roomUpDTO.getActive() || !roomUpDTO.getActive()) {
            room.setActive(roomUpDTO.getActive());
        }
        if (roomUpDTO.getRoomName() != null) {
            room.setRoomName(roomUpDTO.getRoomName());
        }
        if (roomUpDTO.getRoomStatusId() != null) {
            room.setRoomStatus(roomStatusService.getRoomStatusById(roomUpDTO.getRoomStatusId()));
        }
        
        if (roomUpDTO.getStaffIdList() != null) {
            for (int staffId : roomUpDTO.getStaffIdList()){
                room.addStaff(staffService.getStaffById((Integer) staffId));
            }
        }
        roomRepository.save(room);
        return getRoomById(room.getId());
    }
    
}
