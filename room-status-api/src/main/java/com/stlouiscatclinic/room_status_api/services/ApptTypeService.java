package com.stlouiscatclinic.room_status_api.services;

import com.stlouiscatclinic.room_status_api.dto.download.ApptTypeDownDTO;
import com.stlouiscatclinic.room_status_api.dto.mapper.ApptTypeMapper;
import com.stlouiscatclinic.room_status_api.dto.upload.ApptTypeUpDTO;
import com.stlouiscatclinic.room_status_api.models.ApptType;
import com.stlouiscatclinic.room_status_api.repositories.ApptTypeRepository;
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
public class ApptTypeService {
    
    @Autowired
    private ApptTypeRepository apptTypeRepository;
    
    @Autowired
    private ApptTypeMapper apptTypeMapper;
    
    public ApptType createApptType(ApptTypeUpDTO apptTypeUpDTO) {
        return apptTypeRepository.save(new ApptType(apptTypeUpDTO.getApptTypeName()));
    }
    
    public List<ApptTypeDownDTO> getApptTypeDTOList () {
        return apptTypeRepository.findAll().stream().map(apptTypeMapper).collect(Collectors.toList());
    }
    
    public ApptTypeDownDTO getApptTypeById (long id) {
        Optional<ApptTypeDownDTO> result = apptTypeRepository.findById(id).map(apptTypeMapper);
        if (result.isEmpty()) {
            throw new RuntimeException("Appointment Type not found");
        }
        return result.get();
    }
    
    public ApptType updateApptType(long id, ApptTypeUpDTO apptTypeUpDTO) {
        ApptType apptType = apptTypeRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Appointment Type with ID " + id +
                        " not found"));
        apptType.setActive(apptTypeUpDTO.getActive());
        apptType.setApptTypeName(apptTypeUpDTO.getApptTypeName());
        return apptTypeRepository.save(apptType);
    }
}
