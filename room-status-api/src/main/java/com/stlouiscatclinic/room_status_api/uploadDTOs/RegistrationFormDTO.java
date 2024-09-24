package com.stlouiscatclinic.room_status_api.uploadDTOs;

import java.util.List;

/**
 * Created by Trevor Gruber
 */

public class RegistrationFormDTO extends LoginFormDTO{
    
    /* fields */
    
    private String verifyPassword;
    private boolean administrator;
    private String pin;
    private String verifyPin;
    private String firstName;
    private String lastName;
    //TODO test RegistrationFormDTO and List<Integer>. List<String>?
    private List<Integer> positionIdList;
    
    /* Getters and Setters */
    
    public String getVerifyPassword() {
        return verifyPassword;
    }
    
    public boolean isAdministrator() {
        return administrator;
    }
    
    public String getPin() {
        return pin;
    }
    
    public String getVerifyPin() {
        return verifyPin;
    }
    
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