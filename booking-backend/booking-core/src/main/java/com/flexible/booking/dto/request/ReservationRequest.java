package com.flexible.booking.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class ReservationRequest {
    @NotBlank(message = "Username cannot be empty")
    String username;
    @NotNull(message = "Store cannot be empty")
    Integer storeId;
    @NotNull(message = "Reservation date cannot be empty")
    @FutureOrPresent(message = "Reservation date must be in the present or in the future.")
    LocalDate reservationDate;
    @NotBlank(message = "Timeslot cannot be empty")
    String timeslot;
}
