package com.flexible.booking.dto;

import com.flexible.booking.dto.enums.AvailabilityLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvailableDatesInfo {
    String date;
    Integer remainingTimeslots;
    Integer totalTimeslots;
    AvailabilityLevel availabilityLevel;
}
