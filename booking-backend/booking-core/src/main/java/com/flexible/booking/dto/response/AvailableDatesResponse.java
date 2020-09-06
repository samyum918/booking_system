package com.flexible.booking.dto.response;

import com.flexible.booking.dto.AvailableDatesInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AvailableDatesResponse {
    String yearMonth;
    List<AvailableDatesInfo> availableDates = new ArrayList<>();
}
