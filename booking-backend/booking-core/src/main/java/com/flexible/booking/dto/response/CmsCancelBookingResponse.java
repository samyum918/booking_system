package com.flexible.booking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class CmsCancelBookingResponse {
    Integer id;
    Integer storeId;
    String storeName;
    Integer userId;
    String firstName;
    String lastName;
    String username;
    LocalDate reservationDate;
    String timeslot;
}
