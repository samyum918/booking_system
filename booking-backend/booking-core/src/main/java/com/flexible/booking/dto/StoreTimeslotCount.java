package com.flexible.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.DayOfWeek;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class StoreTimeslotCount {
    DayOfWeek weekday;
    Long noOfTimeslot;
}
