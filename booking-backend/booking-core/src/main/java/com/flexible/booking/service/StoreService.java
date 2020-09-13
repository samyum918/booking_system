package com.flexible.booking.service;

import com.flexible.booking.dto.request.StoreRequest;
import com.flexible.booking.exception.ApiBadRequestException;
import com.flexible.booking.exception.ApiForbiddenException;
import com.flexible.booking.exception.ApiResourceNotFoundException;
import com.flexible.booking.model.Store;
import com.flexible.booking.model.StoreTimeslot;
import com.flexible.booking.repository.StoreRepository;
import com.flexible.booking.repository.StoreTimeslotRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreTimeslotRepository storeTimeslotRepository;

    @Transactional
    public Store create(StoreRequest request) {
        Integer existingRecords = storeRepository.countByName(request.getName());
        if(existingRecords > 0) {
            throw new ApiForbiddenException("Store already exists.");
        }

        String[] ignoreProperties = {"workingHourFrom", "workingHourTo", "excludingTimeslots"};
        Store store = new Store();
        try {
            BeanUtils.copyProperties(request, store, ignoreProperties);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to transform store properties.");
        }
        //workingHourFrom, workingHourTo, excludingTimeslots
        LocalTime workingHourFrom = LocalTime.parse(request.getWorkingHourFrom());
        store.setWorkingHourFrom(workingHourFrom.getHour());

        LocalTime workingHourTo = LocalTime.parse(request.getWorkingHourTo());
        store.setWorkingHourTo(workingHourTo.getHour());

        List<String> excludingTimeslots = request.getExcludingTimeslots();
        if(excludingTimeslots == null) {
            excludingTimeslots = new ArrayList<>();
        }

        //save
        storeRepository.save(store);

        //save store timeslot
        List<StoreTimeslot> storeTimeslotList = new ArrayList<>();
        for(DayOfWeek weekday : DayOfWeek.values()) {
            if(!store.getOpenAtMon() && weekday == DayOfWeek.MONDAY)
                continue;
            if(!store.getOpenAtTue() && weekday == DayOfWeek.TUESDAY)
                continue;
            if(!store.getOpenAtWed() && weekday == DayOfWeek.WEDNESDAY)
                continue;
            if(!store.getOpenAtThu() && weekday == DayOfWeek.THURSDAY)
                continue;
            if(!store.getOpenAtFri() && weekday == DayOfWeek.FRIDAY)
                continue;
            if(!store.getOpenAtSat() && weekday == DayOfWeek.SATURDAY)
                continue;
            if(!store.getOpenAtSun() && weekday == DayOfWeek.SUNDAY)
                continue;

            LocalTime startWorkingTime = LocalTime.of(store.getWorkingHourFrom(), 0);
            LocalTime endWorkingTime = LocalTime.of(store.getWorkingHourTo(), 0);
            for(LocalTime time = startWorkingTime; time.isBefore(endWorkingTime); time = time.plusMinutes(store.getBookingDurationInMin())) {
                if(!excludingTimeslots.contains(time.toString())) {
                    StoreTimeslot storeTimeslot = new StoreTimeslot();
                    storeTimeslot.setStoreId(store.getId());
                    storeTimeslot.setWeekday(weekday);
                    storeTimeslot.setStartTime(time.toString());
                    storeTimeslot.setEndTime(time.plusMinutes(store.getBookingDurationInMin()).toString());

                    storeTimeslotList.add(storeTimeslot);
                }
            }
        }
        storeTimeslotRepository.saveAll(storeTimeslotList);

        return store;
    }

    @Transactional
    public Store update(StoreRequest request) {
        if(StringUtils.isEmpty(request.getId())) {
            throw new ApiBadRequestException("Id cannot be null");
        }

        Optional<Store> storeOpt = storeRepository.findById(request.getId());
        if(!storeOpt.isPresent()) {
            throw new ApiResourceNotFoundException("Store cannot be found.");
        }

        String[] ignoreProperties = {"workingHourFrom", "workingHourTo", "excludingTimeslots"};
        Store store = new Store();
        try {
            BeanUtils.copyProperties(request, store, ignoreProperties);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to transform store properties.");
        }
        //workingHourFrom, workingHourTo
        LocalTime workingHourFrom = LocalTime.parse(request.getWorkingHourFrom());
        store.setWorkingHourFrom(workingHourFrom.getHour());

        LocalTime workingHourTo = LocalTime.parse(request.getWorkingHourTo());
        store.setWorkingHourTo(workingHourTo.getHour());

        return storeRepository.save(store);
    }
}
