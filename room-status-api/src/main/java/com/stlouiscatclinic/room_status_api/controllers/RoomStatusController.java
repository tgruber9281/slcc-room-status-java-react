package com.stlouiscatclinic.room_status_api.controllers;

import com.stlouiscatclinic.room_status_api.services.RoomStatusService;
import com.stlouiscatclinic.room_status_api.uploadDTOs.RoomStatusDTO;
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
@RequestMapping("/api/status")
public class RoomStatusController {
    
    @Autowired
    private RoomStatusService roomStatusService;
    
    @PostMapping ("new")
    public ResponseEntity<Map<String,String>> processAddRoomStatusForm (@RequestBody RoomStatusDTO roomStatusDTO) {
        Map<String,String> responseBody = new HashMap<>();
        roomStatusService.createRoomStatus(roomStatusDTO);
        responseBody.put("message", "Status successfully created");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }
}
