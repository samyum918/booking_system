package com.flexible.booking.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class DateBlockRequest {
    @NotNull
    private Integer storeId;
    @NotNull
    private LocalDate date;
}
