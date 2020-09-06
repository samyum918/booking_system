package com.flexible.booking.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AvailableTimeslotsResponse {
    Integer storeId;
    String date;
    List<String> availableTimeslots = new ArrayList<>();
}
