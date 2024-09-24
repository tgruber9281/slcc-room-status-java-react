package com.stlouiscatclinic.room_status_api.services;

import com.stlouiscatclinic.room_status_api.models.ApptType;
import com.stlouiscatclinic.room_status_api.models.RoomStatus;
import com.stlouiscatclinic.room_status_api.repositories.ApptTypeRepository;
import com.stlouiscatclinic.room_status_api.repositories.RoomStatusRepository;
import com.stlouiscatclinic.room_status_api.uploadDTOs.ApptTypeDTO;
import com.stlouiscatclinic.room_status_api.uploadDTOs.RoomStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Trevor Gruber
 */
@Service
public class ApptTypeService {
    
    @Autowired
    private ApptTypeRepository apptTypeRepository;
    
    public ApptType createApptType(ApptTypeDTO apptTypeDTO) {
        return apptTypeRepository.save(new ApptType(apptTypeDTO.getApptTypeName()));
    }
}
