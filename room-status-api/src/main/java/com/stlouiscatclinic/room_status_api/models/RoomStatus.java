package com.stlouiscatclinic.room_status_api.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Trevor Gruber
 */
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class RoomStatus {
    
    /** fields */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean active;
    
    private String statusName;
    
    @OneToMany
    @JoinColumn (name = "roomStatus_id")
    private final Set<Room> rooms = new HashSet<>();
    
    /** Constructor(s) */
    public RoomStatus() {
    }
    
    public RoomStatus(boolean active, String statusName) {
        this.active = active;
        this.statusName = statusName;
    }
    
    /** Custom methods */
    
    public void addRoom (Room room) {
        rooms.add(room);
    }
    
    public void removeRoom (Room room) {
        rooms.remove(room);
    }
    
    /** Getters and Setters */
    
    public long getId() {
        return id;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public String getStatusName() {
        return statusName;
    }
    
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
    
    public Set<Room> getRooms() {
        return rooms;
    }
    
    /** toString */
    @Override
    public String toString() {
        return "RoomStatus{" +
                "id=" + id +
                ", active=" + active +
                ", status='" + statusName + '\'' +
                ", rooms=" + rooms +
                '}';
    }
    
    /** Equals and Hashcode*/
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomStatus that)) return false;
        return id == that.id;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
