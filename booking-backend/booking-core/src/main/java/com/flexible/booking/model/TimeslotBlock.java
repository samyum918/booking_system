package com.flexible.booking.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "timeslot_block")
public class TimeslotBlock extends BaseModel {
    @NotNull
    @Column(name = "store_id")
    private Integer storeId;

    @NotNull
    @Future(message = "Cannot block a timeslot in the past.")
    @Column(name = "date")
    private LocalDate date;

    @NotBlank
    @Column(name = "timeslot")
    private String timeslot;
}
