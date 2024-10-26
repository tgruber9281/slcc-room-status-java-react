package com.stlouiscatclinic.room_status_api.controllers;

import com.stlouiscatclinic.room_status_api.dto.download.RoomDownDTO;
import com.stlouiscatclinic.room_status_api.dto.upload.RoomUpDTO;
import com.stlouiscatclinic.room_status_api.models.Room;
import com.stlouiscatclinic.room_status_api.services.AuthenticationService;
import com.stlouiscatclinic.room_status_api.services.RoomService;
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
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;
    
    @Autowired
    AuthenticationController authenticationController;
    
    @Autowired
    AuthenticationService authenticationService;
    
    @PostMapping("/new")
    public ResponseEntity<Map<String, String>> processAddRoomForm (@RequestBody RoomUpDTO roomUpDTO) {
        Map<String, String> responseBody = new HashMap<>();
        Room room = roomService.createRoom(roomUpDTO);
        responseBody.put("message", "Room successfully created");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }
    
    @GetMapping ("/public/getAll")
    public ResponseEntity<List<RoomDownDTO>> getAllRooms () {
        return new ResponseEntity<>(roomService.getRoomDTOList(),HttpStatus.OK);
    }
    
    @GetMapping ("/public/getById/{id}")
    public ResponseEntity<RoomDownDTO> getRoomById (@PathVariable long id) {
        return new ResponseEntity<>(roomService.getRoomById(id),HttpStatus.OK);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateRoom(@PathVariable long id, @RequestBody RoomUpDTO roomUpDTO) {
        try {
            RoomDownDTO updatedRoom = roomService.updateRoom(id, roomUpDTO);
            return ResponseEntity.ok(updatedRoom);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
}
