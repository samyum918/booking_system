package com.flexible.booking.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;

@Getter
@Setter
@Entity
@Table(
    name = "store_timeslot",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"store_id", "start_time", "end_time"})}
)
public class StoreTimeslot extends BaseModel {
    @NotNull
    @ManyToOne
    @JoinColumn(name="store_id", nullable = false)
    private Store store;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "weekday")
    private DayOfWeek weekday;

    @NotBlank
    @Column(name = "start_time")
    private String startTime;

    @NotBlank
    @Column(name = "end_time")
    private String endTime;
}
