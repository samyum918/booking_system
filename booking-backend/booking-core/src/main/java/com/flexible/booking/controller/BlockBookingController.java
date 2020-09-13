package com.flexible.booking.controller;

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
    public TimeslotBlock blockSpecificTimeslot(@Valid @RequestBody TimeslotBlock timeslotBlock) {
        return blockBookingService.blockSpecificTimeslot(timeslotBlock);
    }

    @PostMapping("/date")
    public DateBlock blockSpecificDate(@Valid @RequestBody DateBlock dateBlock) {
        return blockBookingService.blockSpecificDate(dateBlock);
    }
}
