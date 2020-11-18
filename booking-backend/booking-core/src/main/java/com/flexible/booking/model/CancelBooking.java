package com.flexible.booking.model;

import com.flexible.booking.model.base.BaseModel;
import com.flexible.booking.model.enums.ReservationRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "cancel_booking")
public class CancelBooking extends BaseModel {
    @ManyToOne
    @JoinColumn(name="store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name = "reservation_date")
    private LocalDate reservationDate;

    @Column(name = "timeslot")
    private String timeslot;

    @Enumerated(EnumType.STRING)
    @Column(name = "weekday")
    private DayOfWeek weekday;

    @Enumerated(EnumType.STRING)
    @Column(name = "reserve_by")
    private ReservationRole reserveBy;
}
