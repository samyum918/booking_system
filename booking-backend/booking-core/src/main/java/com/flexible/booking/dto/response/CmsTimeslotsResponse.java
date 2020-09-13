package com.flexible.booking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;

@Getter
@Setter
@AllArgsConstructor
public class CmsTimeslotsResponse {
    Integer id;
    Integer storeId;
    String storeName;
    DayOfWeek weekday;
    String startTime;
    String endTime;
}
