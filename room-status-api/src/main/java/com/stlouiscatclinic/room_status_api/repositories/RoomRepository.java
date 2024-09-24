package com.stlouiscatclinic.room_status_api.repositories;

import com.stlouiscatclinic.room_status_api.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Trevor Gruber
 */

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
