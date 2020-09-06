package com.flexible.booking.repository;

import com.flexible.booking.model.DateBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DateBlockRepository extends JpaRepository<DateBlock, Integer> {
    Integer countByStoreIdAndDate(Integer storeId, LocalDate date);
}
