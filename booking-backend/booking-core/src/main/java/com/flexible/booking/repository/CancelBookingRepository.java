package com.flexible.booking.repository;

import com.flexible.booking.model.CancelBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancelBookingRepository extends JpaRepository<CancelBooking, Integer> {
}
