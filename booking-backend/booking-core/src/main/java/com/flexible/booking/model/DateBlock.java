package com.flexible.booking.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
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
    @Column(name = "store_id")
    private Integer storeId;

    @NotNull
    @Future(message = "Cannot block a date in the past.")
    @Column(name = "date")
    private LocalDate date;
}
