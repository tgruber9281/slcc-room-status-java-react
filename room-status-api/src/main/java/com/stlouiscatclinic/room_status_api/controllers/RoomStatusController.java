package com.stlouiscatclinic.room_status_api.controllers;

import com.stlouiscatclinic.room_status_api.dto.download.RoomStatusDownDTO;
import com.stlouiscatclinic.room_status_api.dto.upload.RoomStatusUpDTO;
import com.stlouiscatclinic.room_status_api.models.RoomStatus;
import com.stlouiscatclinic.room_status_api.services.RoomStatusService;
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
@RequestMapping("/api/status")
public class RoomStatusController {
    
    @Autowired
    private RoomStatusService roomStatusService;
    
    @PostMapping ("new")
    public ResponseEntity<Map<String,String>> processAddRoomStatusForm (@RequestBody RoomStatusUpDTO roomStatusUpDTO) {
        Map<String,String> responseBody = new HashMap<>();
        roomStatusService.createRoomStatus(roomStatusUpDTO);
        responseBody.put("message", "Status successfully created");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }
    
    @GetMapping("/public/getAll")
    public ResponseEntity<List<RoomStatusDownDTO>> getAllRooms () {
        return new ResponseEntity<>(roomStatusService.getRoomStatusDTOList(),HttpStatus.OK);
    }
    
    @GetMapping ("/public/getById/{id}")
    public ResponseEntity<RoomStatusDownDTO> getRoomById (@PathVariable long id) {
        return new ResponseEntity<>(roomStatusService.getRoomStatusDownDTOById(id),HttpStatus.OK);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateRoomStatus(@PathVariable long id,
                                              @RequestBody RoomStatusUpDTO roomStatusUpDTO) {
        try {
            RoomStatus updatedRoomStatus = roomStatusService.updateRoomStatus(id, roomStatusUpDTO);
            return ResponseEntity.ok(updatedRoomStatus);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
