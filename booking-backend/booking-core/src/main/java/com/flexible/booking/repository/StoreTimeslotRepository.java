package com.flexible.booking.repository;

import com.flexible.booking.dto.StoreTimeslotCount;
import com.flexible.booking.model.StoreTimeslot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface StoreTimeslotRepository extends JpaRepository<StoreTimeslot, Integer> {
    @Query("SELECT new com.flexible.booking.dto.StoreTimeslotCount(st.weekday, count(*)) FROM StoreTimeslot st " +
            "WHERE st.storeId = :storeId GROUP BY st.weekday")
    List<StoreTimeslotCount> getStoreTimeslotCountByWeekday(@Param("storeId") Integer storeId);

    List<StoreTimeslot> findByWeekday(DayOfWeek weekday);

    List<StoreTimeslot> findByStoreIdAndWeekday(Integer storeId, DayOfWeek weekday);

    Integer countByStoreIdAndWeekdayAndStartTime(Integer storeId, DayOfWeek weekday, String startTime);
}
