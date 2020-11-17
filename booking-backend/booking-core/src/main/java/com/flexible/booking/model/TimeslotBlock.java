package com.flexible.booking.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @ManyToOne
    @JoinColumn(name="store_id", nullable = false)
    private Store store;

    @NotNull
    @Future(message = "Cannot block a timeslot in the past.")
    @Column(name = "date")
    private LocalDate date;

    @NotBlank
    @Column(name = "timeslot")
    private String timeslot;
}
