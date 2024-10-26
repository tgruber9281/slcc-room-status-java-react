package com.stlouiscatclinic.room_status_api.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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
public class Room {
    
    /** fields */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean active;
    
    private String roomName;
    
    @ManyToOne
    @JoinColumn(name = "roomStatus_id")
    private RoomStatus roomStatus;
    
    @ManyToMany (mappedBy = "roomStaffList")
    private final List<Staff> staffRoomList = new ArrayList<>();
    
    /** Constructor(s) */
    public Room() {
    }
    
    public Room(boolean active, String roomName) {
        this.active = active;
        this.roomName = roomName;
    }
    
    /** Custom methods */
    
    public void addStaff (Staff staff) {
        staffRoomList.add(staff);
    }
    
    public void removeStaff (Staff staff) {
        staffRoomList.remove(staff);
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
    
    public String getRoomName() {
        return roomName;
    }
    
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    
    public RoomStatus getRoomStatus() {
        return roomStatus;
    }
    
    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }
    
    public List<Staff> getStaffRoomList() {
        return staffRoomList;
    }
    
    /** toString */
    @Override
    public String toString() {
        return "Room{" +
                "roomName='" + roomName + '\'' +
                '}';
    }
    
    /** Equals and Hashcode*/
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room room)) return false;
        return id == room.id;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
