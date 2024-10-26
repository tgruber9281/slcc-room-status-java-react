package com.stlouiscatclinic.room_status_api.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

/**
 * Created by Trevor Gruber
 */
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Staff {
    
    public static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    
    /** fields */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean active;
    private boolean administrator;
    
    @NotBlank
    @NotNull
    @Email
    private String emailAddress;
    
    @NotNull
    private String pwdHash;
    
    @NotNull
    @Column(unique = true)
    private String pinHash;
    
    @NotBlank (message = "First name cannot be blank")
    @Size (max = 50, message = "First name cannot be more than 50 characters.")
    private String firstName;
    
    @NotBlank (message = "Last name cannot be blank")
    @Size (max = 50, message = "Last name cannot be more than 50 characters.")
    private String lastName;
    
    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "Staff_Position",
            joinColumns = {@JoinColumn(name = "staff_id")},
            inverseJoinColumns = {@JoinColumn(name = "position_id")}
    )
    private final List<Position> positionList = new ArrayList<>();
    
    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "Staff_Room",
            joinColumns = {@JoinColumn(name = "staff_id")},
            inverseJoinColumns = {@JoinColumn(name = "room_id")}
    )private final List<Room> roomStaffList = new ArrayList<>();
    
    /** Constructor(s) */
    public Staff() {
    }
    
    public Staff(boolean administrator, boolean active, String emailAddress, String pwdHash,
                 String pinHash,
                 String firstName,
                 String lastName) {
        this.administrator = administrator;
        this.active = active;
        this.emailAddress = emailAddress;
        this.pwdHash = encoder.encode(pwdHash);
        this.pinHash = encoder.encode(pinHash);
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    /** Custom methods */
    
    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwdHash);
    }
    
    public boolean isMatchingPin(String pin) {
        return encoder.matches(pin, pinHash);
    }
    
    public void addPosition (Position position) {
        positionList.add(position);
    }
    
    public void emptyPositionList() {
        positionList.clear();
    }
    
    public void removePosition (Position position) {
        positionList.remove(position);
    }
    
    public void addRoom (Room room) {
        roomStaffList.add(room);
    }
    
    public void removeRoom (Room room) {
        roomStaffList.remove(room);
    }
    
    /** Getters and Setters */
    
    public boolean isAdministrator() {
        return administrator;
    }
    
    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public long getId() {
        return id;
    }
    
    public String getEmailAddress() {
        return emailAddress;
    }
    
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    public void setPwdHash(String pwdHash) {
        this.pwdHash = pwdHash;
    }
    
    public void setPinHash(String pinHash) {
        this.pinHash = pinHash;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName (String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public List<Position> getPositionList() {
        return positionList;
    }
    
    public List<Room> getRoomStaffList() {
        return roomStaffList;
    }
    
    /** toString */
    @Override
    public String toString() {
        return "Staff{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
    
    /** Equals and Hashcode*/
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Staff staff)) return false;
        return Objects.equals(emailAddress, staff.emailAddress);
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(emailAddress);
    }
}
