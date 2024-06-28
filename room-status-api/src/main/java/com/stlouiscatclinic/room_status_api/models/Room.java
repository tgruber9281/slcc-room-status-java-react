package com.stlouiscatclinic.room_status_api.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trevor Gruber
 */
@Entity
public class Room {
    
    /** fields */
    @Id
    @GeneratedValue
    private long id;
    
    private boolean active;
    
    private int roomNumber;
    
    @ManyToOne
    private RoomStatus roomStatus;
    
    @ManyToMany
    private final List<Staff> staffList = new ArrayList<>();
    
    /** Constructor(s) */
    
    /** Custom methods */
    
    /** Getters and Setters */
    
    /** toString */
    
    /** Equals and Hashcode*/
    
}
