package com.flexible.booking.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "timeslot_block")
public class TimeslotBlock extends BaseModel {
    @Column(name = "store_id")
    private Integer storeId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "timeslot")
    private String timeslot;
}
