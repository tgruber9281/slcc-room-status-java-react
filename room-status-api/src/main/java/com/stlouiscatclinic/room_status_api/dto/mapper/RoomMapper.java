package com.stlouiscatclinic.room_status_api.dto.mapper;

import com.stlouiscatclinic.room_status_api.dto.download.RoomDownDTO;
import com.stlouiscatclinic.room_status_api.models.Room;
import com.stlouiscatclinic.room_status_api.models.Staff;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Created by Trevor Gruber
 */

@Service
public class RoomMapper implements Function<Room, RoomDownDTO> {
    
    @Override
    public RoomDownDTO apply(Room room) {
        return new RoomDownDTO(
                room.getId(),
                room.isActive(),
                room.getRoomName(),
                room.getRoomStatus(),
                room.getStaffRoomList().stream().map(Staff::getId).toList()
        );
    }
}
