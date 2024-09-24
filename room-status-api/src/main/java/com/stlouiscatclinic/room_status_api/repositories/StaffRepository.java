package com.stlouiscatclinic.room_status_api.repositories;

import com.stlouiscatclinic.room_status_api.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Trevor Gruber
 */

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    Staff findByEmailAddress(String emailAddress);
    Staff findByPinHash(String pinHash);
}
