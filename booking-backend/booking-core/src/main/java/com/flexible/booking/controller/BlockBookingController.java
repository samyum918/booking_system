package com.flexible.booking.controller;

import com.flexible.booking.dto.request.DateBlockRequest;
import com.flexible.booking.dto.request.TimeslotBlockRequest;
import com.flexible.booking.model.DateBlock;
import com.flexible.booking.model.TimeslotBlock;
import com.flexible.booking.service.BlockBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/cms/block-booking")
public class BlockBookingController {
    @Autowired
    private BlockBookingService blockBookingService;

    @PostMapping("/timeslot")
    public TimeslotBlock blockSpecificTimeslot(@Valid @RequestBody TimeslotBlockRequest request) {
        return blockBookingService.blockSpecificTimeslot(request);
    }

    @PostMapping("/date")
    public DateBlock blockSpecificDate(@Valid @RequestBody DateBlockRequest request) {
        return blockBookingService.blockSpecificDate(request);
    }
}
