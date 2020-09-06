package com.flexible.booking.service;

import com.flexible.booking.dto.request.DateBlockRequest;
import com.flexible.booking.dto.request.TimeslotBlockRequest;
import com.flexible.booking.exception.ApiForbiddenException;
import com.flexible.booking.model.*;
import com.flexible.booking.model.enums.ReservationRole;
import com.flexible.booking.repository.*;
import com.flexible.booking.utils.ProjectUtils;
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

    @Autowired
    private CancelBookingRepository cancelBookingRepository;


    @Transactional
    public TimeslotBlock blockSpecificTimeslot(TimeslotBlockRequest request) {
        Integer blockingRecords = timeslotBlockRepository.countByStoreIdAndDateAndTimeslot(request.getStoreId(),
                                                                        request.getDate(), request.getTimeslot());
        if(blockingRecords > 0) {
            throw new ApiForbiddenException("Blocking record already exists.");
        }

        Optional<Booking> bookingRecordOpt = bookingRepository.findByStoreIdAndReservationDateAndTimeslot(request.getStoreId(),
                                                                            request.getDate(), request.getTimeslot());
        if(bookingRecordOpt.isPresent()) {
            Booking bookingRecord = bookingRecordOpt.get();
            CancelBooking cancelBooking = ProjectUtils.transformFrom(bookingRecord, CancelBooking.class);
            cancelBooking.setId(null);
            cancelBooking.setReserveBy(ReservationRole.ADMIN);
            cancelBookingRepository.save(cancelBooking);
            bookingRepository.delete(bookingRecord);
        }

        TimeslotBlock timeslotBlock = ProjectUtils.transformFrom(request, TimeslotBlock.class);
        timeslotBlockRepository.save(timeslotBlock);

        Booking booking = new Booking();
        booking.setStoreId(request.getStoreId());
        booking.setReservationDate(request.getDate());
        booking.setTimeslot(request.getTimeslot());
        booking.setWeekday(request.getDate().getDayOfWeek());
        booking.setReserveBy(ReservationRole.ADMIN);

        bookingRepository.save(booking);

        return timeslotBlock;
    }

    @Transactional
    public DateBlock blockSpecificDate(DateBlockRequest request) {
        Integer existingRecords = dateBlockRepository.countByStoreIdAndDate(request.getStoreId(), request.getDate());
        if(existingRecords > 0) {
            throw new ApiForbiddenException("Record already exists.");
        }

        DateBlock dateBlock = ProjectUtils.transformFrom(request, DateBlock.class);
        dateBlockRepository.save(dateBlock);

        List<StoreTimeslot> timeslotList = storeTimeslotRepository.findByStoreIdAndWeekday(dateBlock.getStoreId(),
                                                                                dateBlock.getDate().getDayOfWeek());
        List<Booking> bookingList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(timeslotList)) {
            for(StoreTimeslot timeslot : timeslotList) {
                Optional<Booking> bookingRecordOpt = bookingRepository.findByStoreIdAndReservationDateAndTimeslot(request.getStoreId(),
                                                                                            request.getDate(), timeslot.getStartTime());
                if(bookingRecordOpt.isPresent()) {
                    Booking bookingRecord = bookingRecordOpt.get();
                    CancelBooking cancelBooking = ProjectUtils.transformFrom(bookingRecord, CancelBooking.class);
                    cancelBooking.setId(null);
                    cancelBooking.setReserveBy(ReservationRole.ADMIN);
                    cancelBookingRepository.save(cancelBooking);
                    bookingRepository.delete(bookingRecord);
                }

                Booking booking = new Booking();
                booking.setStoreId(request.getStoreId());
                booking.setReservationDate(request.getDate());
                booking.setTimeslot(timeslot.getStartTime());
                booking.setWeekday(request.getDate().getDayOfWeek());
                booking.setReserveBy(ReservationRole.ADMIN);

                bookingList.add(booking);
            }
            bookingRepository.saveAll(bookingList);
        }

        return dateBlock;
    }
}
