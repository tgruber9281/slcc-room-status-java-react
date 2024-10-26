package com.stlouiscatclinic.room_status_api.dto.download;

import java.util.List;

/**
 * Created by Trevor Gruber
 */

public record RoomStatusDownDTO(
        long id,
        boolean active,
        String statusName,
        List<Long> roomIds) {
}
