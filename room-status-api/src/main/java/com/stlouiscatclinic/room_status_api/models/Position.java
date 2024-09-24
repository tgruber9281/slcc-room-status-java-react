package com.stlouiscatclinic.room_status_api.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Trevor Gruber
 */

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Position {
    
    /* fields */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean active;
    
    private String positionName;
    
    @ManyToMany (mappedBy = "positionList")
    @JsonIgnore
    private final List<Staff> staffPositionList = new ArrayList<>();
    
    
    /* Constructor(s) */
    
    public Position() {
    }
    
    public Position(String positionName) {
        this.active = true;
        this.positionName = positionName;
    }
    
    /* Custom methods */
    
    /* Getters and Setters */
    
    public long getId() {
        return id;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public String getPositionName() {
        return positionName;
    }
    
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
    
    public List<Staff> getStaffPositionList() {
        return staffPositionList;
    }
    
    /* toString */
    
    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", active=" + active +
                ", positionName='" + positionName + '\'' +
                ", staffPositionList=" + staffPositionList +
                '}';
    }
    
    /* Equals and Hashcode*/
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position position)) return false;
        return id == position.id;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
