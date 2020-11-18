package com.flexible.booking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.flexible.booking.dto.request.BookingHistoryRequest;
import com.flexible.booking.dto.request.ReservationRequest;
import com.flexible.booking.dto.response.AvailableDatesResponse;
import com.flexible.booking.dto.response.AvailableStoresResponse;
import com.flexible.booking.dto.response.AvailableTimeslotsResponse;
import com.flexible.booking.dto.response.BookingHistoryResponse;
import com.flexible.booking.model.Booking;
import com.flexible.booking.model.Store;
import com.flexible.booking.repository.StoreRepository;
import com.flexible.booking.service.BookingService;
import com.flexible.booking.utils.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/front/booking")
public class BookingController {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private StoreRepository storeRepository;

    @GetMapping("/available-stores")
    public List<AvailableStoresResponse> availableStores() {
        List<Store> storeList = storeRepository.findAllStores();
        return ProjectUtils.transformFromInBatch(storeList, AvailableStoresResponse.class);
    }

    @GetMapping("/available-dates")
    public AvailableDatesResponse availableDates(@RequestParam Integer store_id,
                                                @RequestParam String year_month) {
        return bookingService.getAvailableDates(store_id, year_month);
    }

    @GetMapping("/available-timeslots")
    public AvailableTimeslotsResponse availableTimeslots(@RequestParam Integer store_id,
                                                         @RequestParam String date) {
        return bookingService.getAvailableTimeslots(store_id, date);
    }

    @PostMapping("/reserve")
    public Booking reserve(@Valid @RequestBody ReservationRequest request) {
        return bookingService.reserve(request);
    }

    @DeleteMapping("/cancel/{id:\\d+}")
    public ObjectNode cancel(@PathVariable Integer id) {
        bookingService.cancel(id);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("status", "SUCCESS");
        return objectNode;
    }

    @PostMapping("/booking-history")
    public List<BookingHistoryResponse> bookingHistory(@Valid @RequestBody BookingHistoryRequest request) {
        return bookingService.getBookingHistory(request.getUsername());
    }
}
