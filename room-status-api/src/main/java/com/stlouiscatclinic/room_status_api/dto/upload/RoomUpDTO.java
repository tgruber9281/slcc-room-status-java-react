package com.stlouiscatclinic.room_status_api.dto.upload;

import java.util.List;

/**
 * Created by Trevor Gruber
 */

public class RoomUpDTO {
    
    /* fields */
    
    private boolean active;
    private String roomName;
    private Long roomStatusId;
    private List<Integer> staffIdList;
    
    /* Getters and Setters */
    
    public boolean getActive() {return active;}
    public String getRoomName() {
        return roomName;
    }
    
    public Long getRoomStatusId() {
        return roomStatusId;
    }
    
    public List<Integer> getStaffIdList() {
        return staffIdList;
    }
}