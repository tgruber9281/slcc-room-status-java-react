package com.stlouiscatclinic.room_status_api.dto.download;

import com.stlouiscatclinic.room_status_api.models.RoomStatus;

import java.util.List;

/**
 * Created by Trevor Gruber
 */

public record RoomDownDTO(
        long id,
        boolean active,
        String roomName,
        RoomStatus roomStatus,
        List<Long> staffIds) {
}
