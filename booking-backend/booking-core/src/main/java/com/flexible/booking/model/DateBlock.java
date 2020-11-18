package com.flexible.booking.model;

import com.flexible.booking.model.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "date_block")
public class DateBlock extends BaseModel {
    @NotNull
    @ManyToOne
    @JoinColumn(name="store_id", nullable = false)
    private Store store;

    @NotNull
    @Future(message = "Cannot block a date in the past.")
    @Column(name = "date")
    private LocalDate date;
}
