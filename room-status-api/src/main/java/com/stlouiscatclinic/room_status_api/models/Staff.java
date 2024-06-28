package com.stlouiscatclinic.room_status_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trevor Gruber
 */
@Entity
public class Staff {
    
    /** fields */
    @Id
    @GeneratedValue
    private long id;
    
    private boolean active;
    
    @NotBlank (message = "First name cannot be blank")
    @Size (max = 50, message = "First name cannot be more than 50 characters.")
    private String firstName;
    
    @NotBlank (message = "Last name cannot be blank")
    @Size (max = 50, message = "Last name cannot be more than 50 characters.")
    private String lastName;
    
    @ManyToOne
    private Position position;
    
    @ManyToMany (mappedBy = "staffList")
    private final List<Room> roomList = new ArrayList<>();
    
    /** Constructor(s) */
    
    /** Custom methods */
    
    /** Getters and Setters */
    
    /** toString */
    
    /** Equals and Hashcode*/
    
}
