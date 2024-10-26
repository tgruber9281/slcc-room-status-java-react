package com.stlouiscatclinic.room_status_api.dto.mapper;

import com.stlouiscatclinic.room_status_api.dto.download.RoomStatusDownDTO;
import com.stlouiscatclinic.room_status_api.models.Room;
import com.stlouiscatclinic.room_status_api.models.RoomStatus;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Created by Trevor Gruber
 */

@Service
public class RoomStatusMapper implements Function<RoomStatus, RoomStatusDownDTO> {
    
    @Override
    public RoomStatusDownDTO apply(RoomStatus roomStatus) {
        return new RoomStatusDownDTO(
                roomStatus.getId(),
                roomStatus.isActive(),
                roomStatus.getStatusName(),
                roomStatus.getRooms().stream().map(Room::getId).toList()
        );
    }
}
