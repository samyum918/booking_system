package com.flexible.booking.controller;

import com.flexible.booking.dto.response.CmsBookingResponse;
import com.flexible.booking.dto.response.CmsCancelBookingResponse;
import com.flexible.booking.model.*;
import com.flexible.booking.repository.*;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DateBlockRepository dateBlockRepository;

    @Autowired
    private TimeslotBlockRepository timeslotBlockRepository;

    @GetMapping("/booking")
    public List<CmsBookingResponse> getAllBooking() {
        return bookingRepository.cmsFindAllBooking();
    }

    @GetMapping("/cancel-booking")
    public List<CmsCancelBookingResponse> getAllCancelBooking() {
        return cancelBookingRepository.cmsFindAllCancelBooking();
    }

    @GetMapping("/user")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @GetMapping("/date-blocking")
    public List<DateBlock> getAllDateBlock() {
        return dateBlockRepository.findAll();
    }

    @GetMapping("/timeslot-blocking")
    public List<TimeslotBlock> getAllTimeslotBlock() {
        return timeslotBlockRepository.findAll();
    }
}
