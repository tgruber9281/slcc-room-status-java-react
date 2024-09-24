package com.stlouiscatclinic.room_status_api.controllers;

import com.stlouiscatclinic.room_status_api.models.Room;
import com.stlouiscatclinic.room_status_api.services.AuthenticationService;
import com.stlouiscatclinic.room_status_api.services.RoomService;
import com.stlouiscatclinic.room_status_api.uploadDTOs.RoomDTO;
import jakarta.servlet.http.HttpServletRequest;
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
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;
    
    @Autowired
    AuthenticationController authenticationController;
    
    @Autowired
    AuthenticationService authenticationService;
    
    @PostMapping("/new")
    public ResponseEntity<Map<String, String>> processAddRoomForm (@RequestBody RoomDTO roomDTO) {
        Map<String, String> responseBody = new HashMap<>();
        Room room = roomService.createRoom(roomDTO);
        responseBody.put("message", "Room successfully created");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }
    
    
}
