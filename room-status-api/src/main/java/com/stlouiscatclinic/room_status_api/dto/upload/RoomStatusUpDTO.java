package com.stlouiscatclinic.room_status_api.dto.upload;

/**
 * Created by Trevor Gruber
 */

public class RoomStatusUpDTO {
    
    /* fields */
    
    private boolean active;
    private String statusName;
    
    /* Getters and Setters */
    
    public boolean getActive() {return active;}
    public String getStatusName() {
        return statusName;
    }
}
