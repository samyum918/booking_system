package com.flexible.booking.model.base;

import com.flexible.booking.model.base.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@MappedSuperclass
public class SoftDeleteBaseModel extends BaseModel {
    public static final String SOFT_DELETED_CLAUSE = "delete_date IS NULL";

    @Column(name = "delete_date")
    private LocalDateTime deleteDate;
}
