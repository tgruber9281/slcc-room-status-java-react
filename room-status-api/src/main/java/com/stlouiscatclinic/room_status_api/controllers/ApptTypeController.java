package com.stlouiscatclinic.room_status_api.controllers;

import com.stlouiscatclinic.room_status_api.dto.download.ApptTypeDownDTO;
import com.stlouiscatclinic.room_status_api.dto.upload.ApptTypeUpDTO;
import com.stlouiscatclinic.room_status_api.models.ApptType;
import com.stlouiscatclinic.room_status_api.services.ApptTypeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Trevor Gruber
 */
@Controller
@RequestMapping("/api/appttype")
public class ApptTypeController {
    
    @Autowired
    private ApptTypeService apptTypeService;
    
    @PostMapping ("new")
    public ResponseEntity<Map<String,String>> processAddApptTypeForm (@RequestBody ApptTypeUpDTO apptTypeUpDTO) {
        Map<String,String> responseBody = new HashMap<>();
        apptTypeService.createApptType(apptTypeUpDTO);
        responseBody.put("message", "Appointment type successfully created");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }
    
    @GetMapping ("/public/getAll")
    public ResponseEntity<List<ApptTypeDownDTO>> getAllApptTypes () {
        return new ResponseEntity<>(apptTypeService.getApptTypeDTOList(),HttpStatus.OK);
    }
    
    @GetMapping("/public/getById/{id}")
    public ResponseEntity<ApptTypeDownDTO> getApptTypeById (@PathVariable long id) {
        return new ResponseEntity<>(apptTypeService.getApptTypeById(id),HttpStatus.OK);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateApptType(@PathVariable long id,
                                            @RequestBody ApptTypeUpDTO apptTypeUpDTO) {
        try {
            ApptType updatedApptType = apptTypeService.updateApptType(id, apptTypeUpDTO);
            return ResponseEntity.ok(updatedApptType);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
