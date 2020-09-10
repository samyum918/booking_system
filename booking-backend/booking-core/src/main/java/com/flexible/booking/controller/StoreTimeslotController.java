package com.flexible.booking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.flexible.booking.dto.request.CreateStoreTimeslotRequest;
import com.flexible.booking.exception.ApiForbiddenException;
import com.flexible.booking.exception.ApiResourceNotFoundException;
import com.flexible.booking.model.StoreTimeslot;
import com.flexible.booking.repository.StoreTimeslotRepository;
import com.flexible.booking.utils.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cms/store-timeslot")
public class StoreTimeslotController {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StoreTimeslotRepository storeTimeslotRepository;


    @PostMapping("/create")
    public StoreTimeslot create(@Valid @RequestBody CreateStoreTimeslotRequest request) {
        Integer existingRecords = storeTimeslotRepository.countByStoreIdAndWeekdayAndStartTime(request.getStoreId(),
                                                                        request.getWeekday(), request.getStartTime());
        if(existingRecords > 0) {
            throw new ApiForbiddenException("Store timeslot already exists.");
        }

        StoreTimeslot storeTimeslot = ProjectUtils.transformFrom(request, StoreTimeslot.class);
        return storeTimeslotRepository.save(storeTimeslot);
    }

    @GetMapping("/get")
    public List<StoreTimeslot> getAll() {
        return storeTimeslotRepository.findAll();
    }

    @GetMapping("/get/{id:\\d+}")
    public StoreTimeslot getOne(@PathVariable Integer id) {
        return storeTimeslotRepository.findById(id).orElseThrow(() -> new ApiResourceNotFoundException("Store does not exist."));
    }

    @DeleteMapping("/delete/{id:\\d+}")
    public ObjectNode delete(@PathVariable Integer id) {
        storeTimeslotRepository.deleteById(id);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("status", "SUCCESS");
        return objectNode;
    }
}
