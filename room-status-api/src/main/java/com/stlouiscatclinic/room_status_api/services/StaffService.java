package com.stlouiscatclinic.room_status_api.services;

import com.stlouiscatclinic.room_status_api.dto.download.StaffDownDTO;
import com.stlouiscatclinic.room_status_api.dto.mapper.StaffMapper;
import com.stlouiscatclinic.room_status_api.dto.upload.StaffUpDTO;
import com.stlouiscatclinic.room_status_api.models.Staff;
import com.stlouiscatclinic.room_status_api.repositories.StaffRepository;
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
public class StaffService {
    
    @Autowired
    private StaffRepository staffRepository;
    
    @Autowired
    private StaffMapper staffMapper;
    
    @Autowired
    private PositionService positionService;
    
    public Staff getStaffById (Integer id) {
        Staff staff =  staffRepository.findById((long) id).get();
        return staff;
    }
    
    public List<StaffDownDTO> getStaffDTOList() {
        return staffRepository.findAll().stream().map(staffMapper).collect(Collectors.toList());
    }
    
    public StaffDownDTO getStaffDownDTOById(long id) {
        Optional<StaffDownDTO> result = staffRepository.findById(id).map(staffMapper);
        if (result.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return result.get();
    }
    
    public Staff updateStaff(long id, StaffUpDTO staffUpDTO) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Staff with ID " + id + " not " +
                        "found"));
        staff.setEmailAddress(staffUpDTO.getEmailAddress());
        staff.setActive(staffUpDTO.getActive());
        staff.setAdministrator(staffUpDTO.isAdministrator());
        staff.setFirstName(staffUpDTO.getFirstName());
        staff.setLastName(staffUpDTO.getLastName());
        staff.emptyPositionList();
        for (int positionId : staffUpDTO.getPositionIdList()) {
            staff.addPosition(positionService.getPositionById((Integer) positionId));
        }
        return staffRepository.save(staff);
    }
}
