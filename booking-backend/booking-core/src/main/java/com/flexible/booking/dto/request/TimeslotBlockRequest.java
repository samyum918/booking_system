package com.flexible.booking.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class TimeslotBlockRequest {
    @NotNull
    private Integer storeId;
    @NotNull
    private LocalDate date;
    @NotBlank
    private String timeslot;
}
