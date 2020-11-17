package com.flexible.booking.repository;

import com.flexible.booking.model.TimeslotBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TimeslotBlockRepository extends JpaRepository<TimeslotBlock, Integer> {
    Integer countByStore_IdAndDateAndTimeslot(Integer storeId, LocalDate date, String timeslot);
}
