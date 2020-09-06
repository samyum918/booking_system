package com.flexible.booking.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "store")
public class Store extends BaseModel {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "can_book")
    private Boolean canBook;

    @Column(name = "booking_duration_in_min", nullable = false)
    private Integer bookingDurationInMin;

    @Column(name = "working_hour_from")
    private Integer workingHourFrom;

    @Column(name = "working_hour_to")
    private Integer workingHourTo;

    @Column(name = "open_at_sun")
    private Boolean openAtSun;

    @Column(name = "open_at_mon")
    private Boolean openAtMon;

    @Column(name = "open_at_tue")
    private Boolean openAtTue;

    @Column(name = "open_at_wed")
    private Boolean openAtWed;

    @Column(name = "open_at_thu")
    private Boolean openAtThu;

    @Column(name = "open_at_fri")
    private Boolean openAtFri;

    @Column(name = "open_at_sat")
    private Boolean openAtSat;

    @Column(name = "delete_date")
    private LocalDateTime deleteDate;
}
