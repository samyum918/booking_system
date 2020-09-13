package com.flexible.booking.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class StoreRequest {
    Integer id;
    @NotBlank
    String name;
    @NotNull
    Boolean canBook;
    @NotNull
    Integer bookingDurationInMin;
    @NotBlank
    String workingHourFrom;
    @NotBlank
    String workingHourTo;
    List<String> excludingTimeslots;
    Boolean openAtMon;
    Boolean openAtTue;
    Boolean openAtWed;
    Boolean openAtThu;
    Boolean openAtFri;
    Boolean openAtSat;
    Boolean openAtSun;
    LocalDateTime deleteDate;
}
