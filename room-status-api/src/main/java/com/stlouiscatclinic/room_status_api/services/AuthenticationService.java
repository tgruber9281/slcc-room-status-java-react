package com.stlouiscatclinic.room_status_api.services;

import com.stlouiscatclinic.room_status_api.dto.upload.RegistrationFormDTO;
import com.stlouiscatclinic.room_status_api.models.Staff;
import com.stlouiscatclinic.room_status_api.repositories.StaffRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Trevor Gruber
 */
@Service
public class AuthenticationService {
    private static final String staffSessionKey = "staff";
    
    @Autowired
    private StaffRepository staffRepository;
    
    @Autowired
    private PositionService positionService;
    
    public boolean isStaffLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Staff staff = getStaffFromSession(session);
        return staff != null;
    }
    
    public Staff getStaffFromSession(HttpSession session) {
        Long userId = (Long) session.getAttribute(staffSessionKey);
        if (userId == null) {
            return null;
        }
        Optional<Staff> staff = staffRepository.findById(userId);
        return staff.orElse(null);
    }
    
    public void setStaffSessionKey(HttpSession session, Staff staff) {
        session.setAttribute(staffSessionKey, staff.getId());
    }
    
    public Staff processRegistrationForm(RegistrationFormDTO registrationFormDTO) {
        Staff newStaff = new Staff(
                registrationFormDTO.isAdministrator(),
                registrationFormDTO.getActive(),
                registrationFormDTO.getEmailAddress(), registrationFormDTO.getPassword(),
                registrationFormDTO.getPin(), registrationFormDTO.getFirstName(),
                registrationFormDTO.getLastName());
        for (int id : registrationFormDTO.getPositionIdList()) {
            newStaff.addPosition(positionService.getPositionById((Integer) id));
        }
        return staffRepository.save(newStaff);
    }
}
