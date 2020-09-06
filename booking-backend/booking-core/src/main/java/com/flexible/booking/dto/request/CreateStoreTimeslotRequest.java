package com.flexible.booking.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;

@Getter
@Setter
public class CreateStoreTimeslotRequest {
    @NotNull
    Integer storeId;
    @NotNull
    DayOfWeek weekday;
    @NotBlank
    String startTime;
    @NotBlank
    String endTime;
}
