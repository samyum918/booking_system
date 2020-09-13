package com.flexible.booking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.flexible.booking.dto.response.CmsTimeslotsResponse;
import com.flexible.booking.exception.ApiBadRequestException;
import com.flexible.booking.exception.ApiForbiddenException;
import com.flexible.booking.exception.ApiResourceNotFoundException;
import com.flexible.booking.model.StoreTimeslot;
import com.flexible.booking.repository.StoreTimeslotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cms/store-timeslot")
public class StoreTimeslotController {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StoreTimeslotRepository storeTimeslotRepository;


    @PostMapping("/create")
    public StoreTimeslot create(@Valid @RequestBody StoreTimeslot storeTimeslot) {
        Integer existingRecords = storeTimeslotRepository.countByStoreIdAndWeekdayAndStartTime(storeTimeslot.getStoreId(),
                                                                        storeTimeslot.getWeekday(), storeTimeslot.getStartTime());
        if(existingRecords > 0) {
            throw new ApiForbiddenException("Store timeslot already exists.");
        }
        return storeTimeslotRepository.save(storeTimeslot);
    }

    @GetMapping("/get")
    public List<CmsTimeslotsResponse> getAll() {
        return storeTimeslotRepository.cmsFindAllTimeslots();
    }

    @GetMapping("/get/{id:\\d+}")
    public StoreTimeslot getOne(@PathVariable Integer id) {
        return storeTimeslotRepository.findById(id).orElseThrow(() -> new ApiResourceNotFoundException("Store does not exist."));
    }

    @PostMapping("/update")
    public StoreTimeslot update(@Valid @RequestBody StoreTimeslot storeTimeslot) {
        if(StringUtils.isEmpty(storeTimeslot)) {
            throw new ApiBadRequestException("Id cannot be null.");
        }

        Optional<StoreTimeslot> storeTimeslotOpt = storeTimeslotRepository.findById(storeTimeslot.getId());
        if(!storeTimeslotOpt.isPresent()) {
            throw new ApiResourceNotFoundException("Store timeslot cannot be found.");
        }

        return storeTimeslotRepository.save(storeTimeslot);
    }

    @DeleteMapping("/delete/{id:\\d+}")
    public ObjectNode delete(@PathVariable Integer id) {
        storeTimeslotRepository.deleteById(id);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("status", "SUCCESS");
        return objectNode;
    }
}
