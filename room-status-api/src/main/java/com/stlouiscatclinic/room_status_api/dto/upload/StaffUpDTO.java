package com.stlouiscatclinic.room_status_api.dto.upload;

import java.util.List;

/**
 * Created by Trevor Gruber
 */

public class StaffUpDTO{
    
    /* fields */
    
    private boolean administrator;
    private String emailAddress;
    private boolean active;
    private String firstName;
    private String lastName;
    private List<Integer> positionIdList;
    
    /* Getters and Setters */
    
    public String getEmailAddress() {
        return emailAddress;
    }
    
    public boolean isAdministrator() {
        return administrator;
    }
    
    public boolean getActive() {return active; }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public List<Integer> getPositionIdList() {
        return positionIdList;
    }
}