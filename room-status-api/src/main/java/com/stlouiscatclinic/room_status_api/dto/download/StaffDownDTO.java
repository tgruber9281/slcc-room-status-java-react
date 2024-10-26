package com.stlouiscatclinic.room_status_api.dto.download;

import java.util.List;

/**
 * Created by Trevor Gruber
 */

public record StaffDownDTO(
        long id,
        boolean active,
        boolean administrator,
        String emailAddress,
        String firstName,
        String lastName,
        List<Long> positionIds,
        List<Long> roomIds){
}
