package com.flexible.booking.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BookingHistoryResponse {
    Integer bookingId;
    String storeName;
    LocalDate reservationDate;
    String timeslot;
    Boolean canCancel;
}
