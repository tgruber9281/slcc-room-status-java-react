package com.stlouiscatclinic.room_status_api.controllers;

import com.stlouiscatclinic.room_status_api.dto.download.PositionDownDTO;
import com.stlouiscatclinic.room_status_api.dto.upload.PositionUpDTO;
import com.stlouiscatclinic.room_status_api.models.Position;
import com.stlouiscatclinic.room_status_api.services.PositionService;
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
@RequestMapping("/api/position")
public class PositionController {
    
    @Autowired
    PositionService positionService;
    
    @PostMapping ("new")
    public ResponseEntity<Map<String,String>> processAddPositionForm (@RequestBody PositionUpDTO positionUpDTO) {
        Map<String, String> responseBody = new HashMap<>();
        positionService.createPosition(positionUpDTO);
        responseBody.put("message", "Position successfully created");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }
    
    @GetMapping ("/public/getAll")
    public ResponseEntity<List<PositionDownDTO>> getAllPositions () {
        return new ResponseEntity<>(positionService.getPositionDTOList(),HttpStatus.OK);
    }
    
    @GetMapping ("/public/getById/{id}")
    public ResponseEntity<PositionDownDTO> getPositionById (@PathVariable long id) {
        return new ResponseEntity<>(positionService.getPositionDownDTOById(id),HttpStatus.OK);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePosition(@PathVariable long id,
                                           @RequestBody PositionUpDTO positionUpDTO) {
        try {
            Position updatedPosition = positionService.updatePosition(id, positionUpDTO);
            return ResponseEntity.ok(updatedPosition);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
}
