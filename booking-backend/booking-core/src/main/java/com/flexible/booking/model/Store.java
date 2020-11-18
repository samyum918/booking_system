package com.flexible.booking.model;

import com.flexible.booking.model.base.SoftDeleteBaseModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Where(clause = SoftDeleteBaseModel.SOFT_DELETED_CLAUSE)
@Table(name = "store")
public class Store extends SoftDeleteBaseModel {
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
}
