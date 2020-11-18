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
    @Query("SELECT b FROM Booking b WHERE b.store.id = :storeId " +
            "AND SUBSTRING(b.reservationDate, 1, 7) = :yearMonth")
    List<Booking> getBookingRecordByMonth(@Param("storeId") Integer storeId,
                                          @Param("yearMonth") String yearMonth);

    @Query("SELECT new com.flexible.booking.dto.response.CmsBookingResponse(" +
            "b.id, s.id, s.name, u.id, u.firstName, u.lastName, u.username, b.reservationDate, b.timeslot" +
            ") " +
            "FROM Booking b " +
            "INNER JOIN Store s ON b.store.id = s.id " +
            "INNER JOIN User u ON b.user.id = u.id " +
            "ORDER BY b.reservationDate DESC")
    List<CmsBookingResponse> cmsFindAllBooking();

    List<Booking> findByUser_IdOrderByReservationDateDescTimeslotDesc(Integer userId);

    List<Booking> findByStore_IdAndReservationDate(Integer storeId, LocalDate reservationDate);

    Optional<Booking> findByStore_IdAndReservationDateAndTimeslot(Integer storeId, LocalDate reservationDate, String timeslot);
}