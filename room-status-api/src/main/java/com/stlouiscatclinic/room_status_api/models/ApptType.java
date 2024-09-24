package com.stlouiscatclinic.room_status_api.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Created by Trevor Gruber
 */
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class ApptType {
    
    /** fields */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean active;
    
    private String apptTypeName;
    
    
    /** Constructor(s) */
    public ApptType() {
    }
    
    public ApptType(String apptTypeName) {
        this.active = true;
        this.apptTypeName = apptTypeName;
    }
    
    /** Custom methods */
    
    /** Getters and Setters */
    
    /** toString */
    
    /** Equals and Hashcode*/
    
}
