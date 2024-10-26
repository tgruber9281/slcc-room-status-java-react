package com.stlouiscatclinic.room_status_api.dto.mapper;

import com.stlouiscatclinic.room_status_api.dto.download.ApptTypeDownDTO;
import com.stlouiscatclinic.room_status_api.dto.download.RoomDownDTO;
import com.stlouiscatclinic.room_status_api.models.ApptType;
import com.stlouiscatclinic.room_status_api.models.Room;
import com.stlouiscatclinic.room_status_api.models.Staff;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Created by Trevor Gruber
 */

@Service
public class ApptTypeMapper implements Function<ApptType, ApptTypeDownDTO> {
    
    @Override
    public ApptTypeDownDTO apply(ApptType apptType) {
        return new ApptTypeDownDTO(
                apptType.getId(),
                apptType.isActive(),
                apptType.getApptTypeName()
        );
    }
}
