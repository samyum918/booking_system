package com.flexible.booking.controller;

import com.flexible.booking.model.Booking;
import com.flexible.booking.model.CancelBooking;
import com.flexible.booking.repository.BookingRepository;
import com.flexible.booking.repository.CancelBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cms/records")
public class CmsRecordsController {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CancelBookingRepository cancelBookingRepository;

    @GetMapping("/booking")
    public List<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }

    @GetMapping("/cancel-booking")
    public List<CancelBooking> getAllCancelBooking() {
        return cancelBookingRepository.findAll();
    }
}
