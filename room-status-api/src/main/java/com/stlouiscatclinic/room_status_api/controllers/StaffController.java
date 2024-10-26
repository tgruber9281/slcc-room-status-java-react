package com.stlouiscatclinic.room_status_api.controllers;

import com.stlouiscatclinic.room_status_api.dto.download.StaffDownDTO;
import com.stlouiscatclinic.room_status_api.dto.upload.StaffUpDTO;
import com.stlouiscatclinic.room_status_api.models.Staff;
import com.stlouiscatclinic.room_status_api.services.StaffService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Trevor Gruber
 */
@Controller
@RequestMapping("/api/staff")
public class StaffController {
    
    @Autowired
    private StaffService staffService;
    
    @GetMapping ("/public/getAll")
    public ResponseEntity<List<StaffDownDTO>> getAllStaff (){
        return new ResponseEntity<>(staffService.getStaffDTOList(),HttpStatus.OK);
    }
    
    @GetMapping("/public/getById/{id}")
    public ResponseEntity<StaffDownDTO> getStaffById (@PathVariable long id){
        return  new ResponseEntity<>(staffService.getStaffDownDTOById(id),HttpStatus.OK);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateStaff(@PathVariable long id, @RequestBody StaffUpDTO staffUpDTO) {
        try {
            Staff updatedStaff = staffService.updateStaff(id, staffUpDTO);
            return ResponseEntity.ok(updatedStaff);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
}
