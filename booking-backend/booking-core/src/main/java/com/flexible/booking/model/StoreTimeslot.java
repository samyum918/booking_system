package com.flexible.booking.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;

@Getter
@Setter
@Entity
@Table(name = "store_timeslot")
public class StoreTimeslot extends BaseModel {
    @Column(name = "store_id")
    private Integer storeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "weekday")
    private DayOfWeek weekday;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;
}
