package com.stlouiscatclinic.room_status_api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Created by Trevor Gruber
 */
@Entity
public class ApptType {
    
    /** fields */
    @Id
    @GeneratedValue
    private long id;
    
    private String type;
    private boolean active;
    
    /** Constructor(s) */
    
    /** Custom methods */
    
    /** Getters and Setters */
    
    /** toString */
    
    /** Equals and Hashcode*/
    
}
