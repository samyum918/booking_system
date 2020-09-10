package com.flexible.booking.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class DateBlockRequest {
    @NotNull
    private Integer storeId;
    @NotNull
    @Future(message = "Cannot block a date in the past.")
    private LocalDate date;
}
