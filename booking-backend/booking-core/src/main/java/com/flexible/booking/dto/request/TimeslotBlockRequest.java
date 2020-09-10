package com.flexible.booking.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class TimeslotBlockRequest {
    @NotNull
    private Integer storeId;
    @NotNull
    @Future(message = "Cannot block a timeslot in the past.")
    private LocalDate date;
    @NotBlank
    private String timeslot;
}
