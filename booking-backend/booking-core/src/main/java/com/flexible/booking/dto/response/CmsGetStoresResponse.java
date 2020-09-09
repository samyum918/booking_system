package com.flexible.booking.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsGetStoresResponse {
    Integer id;
    String name;
    Boolean canBook;
    Integer bookingDurationInMin;
    Integer workingHourFrom;
    Integer workingHourTo;
    Boolean openAtSun;
    Boolean openAtMon;
    Boolean openAtTue;
    Boolean openAtWed;
    Boolean openAtThu;
    Boolean openAtFri;
    Boolean openAtSat;
}
