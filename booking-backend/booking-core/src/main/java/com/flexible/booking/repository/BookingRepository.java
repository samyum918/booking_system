package com.flexible.booking.repository;

import com.flexible.booking.dto.response.CmsBookingResponse;
import com.flexible.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Query("SELECT b FROM Booking b WHERE b.storeId = :storeId " +
            "AND SUBSTRING(b.reservationDate, 1, 7) = :yearMonth")
    List<Booking> getBookingRecordByMonth(@Param("storeId") Integer storeId,
                                          @Param("yearMonth") String yearMonth);

    @Query("SELECT new com.flexible.booking.dto.response.CmsBookingResponse(" +
            "b.id, b.storeId, s.name, b.userId, u.firstName, u.lastName, u.username, b.reservationDate, b.timeslot" +
            ") " +
            "FROM Booking b " +
            "INNER JOIN Store s ON b.storeId = s.id " +
            "INNER JOIN User u ON b.userId = u.id " +
            "ORDER BY b.reservationDate DESC")
    List<CmsBookingResponse> cmsFindAllBooking();

    List<Booking> findByStoreIdAndReservationDate(Integer storeId, LocalDate reservationDate);

    Optional<Booking> findByStoreIdAndReservationDateAndTimeslot(Integer storeId, LocalDate reservationDate, String timeslot);
}