package com.stlouiscatclinic.room_status_api.controllers;

import com.stlouiscatclinic.room_status_api.services.PositionService;
import com.stlouiscatclinic.room_status_api.uploadDTOs.PositionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
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
    public ResponseEntity<Map<String,String>> processAddPositionForm (@RequestBody PositionDTO positionDTO) {
        Map<String, String> responseBody = new HashMap<>();
        positionService.createPosition(positionDTO);
        responseBody.put("message", "Position successfully created");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }
    
}
