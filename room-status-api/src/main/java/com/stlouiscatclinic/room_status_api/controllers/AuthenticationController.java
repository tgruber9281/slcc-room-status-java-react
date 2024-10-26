package com.stlouiscatclinic.room_status_api.controllers;

import com.stlouiscatclinic.room_status_api.models.Staff;
import com.stlouiscatclinic.room_status_api.repositories.StaffRepository;
import com.stlouiscatclinic.room_status_api.services.AuthenticationService;
import com.stlouiscatclinic.room_status_api.dto.upload.LoginFormDTO;
import com.stlouiscatclinic.room_status_api.dto.upload.RegistrationFormDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Trevor Gruber
 */

@RestController
@RequestMapping("/api")
public class AuthenticationController {
    
    @Autowired
    private StaffRepository staffRepository;
    
    @Autowired
    AuthenticationService authenticationService;
    
    @PostMapping("/public/register")
    public ResponseEntity<Map<String, String>> processRegistrationForm(@RequestBody RegistrationFormDTO registrationFormDTO) {
        Map<String, String> responseBody = new HashMap<>();
        Staff existingStaffByEmail =
                staffRepository.findByEmailAddress(registrationFormDTO.getEmailAddress());
        Staff existingStaffByPin =
                staffRepository.findByPinHash(Staff.encoder.encode(registrationFormDTO.getPin()));
        if (existingStaffByEmail != null) {
            responseBody.put("message",
                    "User with email address: " + existingStaffByEmail.getEmailAddress() + " already " +
                            "exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
        } else if (!registrationFormDTO.getPassword().equals(registrationFormDTO.getVerifyPassword())) {
            responseBody.put("message", "Passwords don't match");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
        } else if (!registrationFormDTO.getPin().equals(registrationFormDTO.getVerifyPin())) {
            responseBody.put("message", "PINs don't match");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
        } else if (existingStaffByPin != null) {
            responseBody.put("message", "User with PIN: " + registrationFormDTO.getPin() + " " +
                    "already exists, please choose a different PIN");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
        }
        authenticationService.processRegistrationForm(registrationFormDTO);
        responseBody.put("message", "User successfully created");
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    
    @PostMapping("/public/login")
    public ResponseEntity<Map<String, String>> processLoginForm (@RequestBody LoginFormDTO loginFormDTO, HttpServletRequest request) {
        Map<String, String> responseBody = new HashMap<>();
        
        Staff staff = staffRepository.findByEmailAddress(loginFormDTO.getEmailAddress());
        if (staff == null || !staff.isMatchingPassword(loginFormDTO.getPassword())) {
            responseBody.put("message", "User does not exist or password doesn't match");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
        }
        
        authenticationService.setStaffSessionKey(request.getSession(), staff);
        responseBody.put("message", "User successfully logged in");
        responseBody.put("administrator", String.valueOf(staff.isAdministrator()));
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    
    @PostMapping("/public/logout")
    public ResponseEntity<Map<String,String>> processLogout(HttpServletRequest request){
        Map<String, String> responseBody = new HashMap<>();
        request.getSession().invalidate();
        responseBody.put("message","User logged out");
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
