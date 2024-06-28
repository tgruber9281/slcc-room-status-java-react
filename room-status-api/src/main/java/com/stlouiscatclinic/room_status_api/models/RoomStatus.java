package com.stlouiscatclinic.room_status_api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Trevor Gruber
 */
@Entity
public class RoomStatus {
    
    /** fields */
    @Id
    @GeneratedValue
    private long id;
    
    private String status;
    
    @OneToMany (mappedBy = "roomStatus")
    private final Set<Room> rooms = new HashSet<>();
    
    /** Constructor(s) */
    
    /** Custom methods */
    
    /** Getters and Setters */
    
    /** toString */
    
    /** Equals and Hashcode*/
    
}
