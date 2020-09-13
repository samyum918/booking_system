package com.flexible.booking.repository;

import com.flexible.booking.dto.response.CmsCancelBookingResponse;
import com.flexible.booking.model.CancelBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CancelBookingRepository extends JpaRepository<CancelBooking, Integer> {
    @Query("SELECT new com.flexible.booking.dto.response.CmsCancelBookingResponse(" +
            "c.id, c.storeId, s.name, c.userId, u.firstName, u.lastName, u.username, c.reservationDate, c.timeslot" +
            ") " +
            "FROM CancelBooking c " +
            "INNER JOIN Store s ON c.storeId = s.id " +
            "INNER JOIN User u ON c.userId = u.id " +
            "ORDER BY c.reservationDate DESC")
    List<CmsCancelBookingResponse> cmsFindAllCancelBooking();
}
