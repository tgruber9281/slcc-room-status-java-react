package com.stlouiscatclinic.room_status_api.repositories;

import com.stlouiscatclinic.room_status_api.models.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Trevor Gruber
 */

@Repository
public interface RoomStatusRepository extends JpaRepository<RoomStatus, Long> {
}
