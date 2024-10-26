package com.stlouiscatclinic.room_status_api.dto.upload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Created by Trevor Gruber
 */

public class LoginFormDTO {
    
    /* fields */
    @NotBlank
    @NotNull
    @Email
    private String emailAddress;
    
    @NotNull
    @NotBlank
    @Size(min = 6, max = 30, message = "Invalid password. Must be between 5 and 30 characters.")
    private String password;
    
    /* Getters and Setters */
    
    public String getEmailAddress() {
        return emailAddress;
    }
    
    public String getPassword() {
        return password;
    }
    
}