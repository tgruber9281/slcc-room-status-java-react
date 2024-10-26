package com.stlouiscatclinic.room_status_api.dto.download;

import java.util.List;

/**
 * Created by Trevor Gruber
 */

public record PositionDownDTO(
        long id,
        boolean active,
        String positionName,
        List<Long> staffIds) {
}
