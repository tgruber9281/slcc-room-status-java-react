package com.stlouiscatclinic.room_status_api.dto.mapper;

import com.stlouiscatclinic.room_status_api.dto.download.StaffDownDTO;
import com.stlouiscatclinic.room_status_api.models.Position;
import com.stlouiscatclinic.room_status_api.models.Room;
import com.stlouiscatclinic.room_status_api.models.Staff;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Created by Trevor Gruber
 */

@Service
public class StaffMapper implements Function<Staff, StaffDownDTO> {
    
    @Override
    public StaffDownDTO apply(Staff staff) {
        return new StaffDownDTO(
                staff.getId(),
                staff.isActive(),
                staff.isAdministrator(),
                staff.getEmailAddress(),
                staff.getFirstName(),
                staff.getLastName(),
                staff.getPositionList().stream().map(Position::getId).toList(),
                staff.getRoomStaffList().stream().map(Room::getId).toList()
        );
    }
}
