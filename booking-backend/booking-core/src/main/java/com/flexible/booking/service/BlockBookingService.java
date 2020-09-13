package com.flexible.booking.service;

import com.flexible.booking.exception.ApiForbiddenException;
import com.flexible.booking.model.*;
import com.flexible.booking.model.enums.ReservationRole;
import com.flexible.booking.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlockBookingService {
    @Autowired
    private DateBlockRepository dateBlockRepository;

    @Autowired
    private TimeslotBlockRepository timeslotBlockRepository;

    @Autowired
    private StoreTimeslotRepository storeTimeslotRepository;

    @Autowired
    private BookingRepository bookingRepository;


    @Transactional
    public TimeslotBlock blockSpecificTimeslot(TimeslotBlock timeslotBlock) {
        Integer blockingRecords = timeslotBlockRepository.countByStoreIdAndDateAndTimeslot(timeslotBlock.getStoreId(),
                                                                        timeslotBlock.getDate(), timeslotBlock.getTimeslot());
        if(blockingRecords > 0) {
            throw new ApiForbiddenException("Blocking record already exists.");
        }

        Optional<Booking> bookingRecordOpt = bookingRepository.findByStoreIdAndReservationDateAndTimeslot(timeslotBlock.getStoreId(),
                                                                            timeslotBlock.getDate(), timeslotBlock.getTimeslot());
        if(bookingRecordOpt.isPresent()) {
            throw new ApiForbiddenException("The timeslot has already reserved by an user. Please cancel the reservation first.");
        }

        //save
        timeslotBlockRepository.save(timeslotBlock);

        Booking booking = new Booking();
        booking.setStoreId(timeslotBlock.getStoreId());
        booking.setReservationDate(timeslotBlock.getDate());
        booking.setTimeslot(timeslotBlock.getTimeslot());
        booking.setWeekday(timeslotBlock.getDate().getDayOfWeek());
        booking.setReserveBy(ReservationRole.ADMIN);

        bookingRepository.save(booking);

        return timeslotBlock;
    }

    @Transactional
    public DateBlock blockSpecificDate(DateBlock dateBlock) {
        Integer existingRecords = dateBlockRepository.countByStoreIdAndDate(dateBlock.getStoreId(), dateBlock.getDate());
        if(existingRecords > 0) {
            throw new ApiForbiddenException("Record already exists.");
        }

        //save
        dateBlockRepository.save(dateBlock);

        List<StoreTimeslot> timeslotList = storeTimeslotRepository.findByStoreIdAndWeekday(dateBlock.getStoreId(),
                                                                                dateBlock.getDate().getDayOfWeek());
        List<Booking> bookingList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(timeslotList)) {
            for(StoreTimeslot timeslot : timeslotList) {
                Optional<Booking> bookingRecordOpt = bookingRepository.findByStoreIdAndReservationDateAndTimeslot(dateBlock.getStoreId(),
                                                                                            dateBlock.getDate(), timeslot.getStartTime());
                if(bookingRecordOpt.isPresent()) {
                    throw new ApiForbiddenException("A timeslot has already reserved by an user. Please cancel the reservation first.");
                }

                Booking booking = new Booking();
                booking.setStoreId(dateBlock.getStoreId());
                booking.setReservationDate(dateBlock.getDate());
                booking.setTimeslot(timeslot.getStartTime());
                booking.setWeekday(dateBlock.getDate().getDayOfWeek());
                booking.setReserveBy(ReservationRole.ADMIN);

                bookingList.add(booking);
            }
            bookingRepository.saveAll(bookingList);
        }

        return dateBlock;
    }
}
