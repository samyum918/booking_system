package com.flexible.booking.model;

import com.flexible.booking.model.enums.ReservationRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "booking")
public class Booking extends BaseModel {
    @Column(name = "store_id", nullable = false)
    private Integer storeId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "reservation_date", nullable = false)
    private LocalDate reservationDate;

    @Column(name = "timeslot", nullable = false)
    private String timeslot;

    @Enumerated(EnumType.STRING)
    @Column(name = "weekday")
    private DayOfWeek weekday;

    @Enumerated(EnumType.STRING)
    @Column(name = "reserve_by")
    private ReservationRole reserveBy;
}
