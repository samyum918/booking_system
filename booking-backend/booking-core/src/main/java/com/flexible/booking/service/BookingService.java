package com.flexible.booking.service;

import com.flexible.booking.dto.AvailableDatesInfo;
import com.flexible.booking.dto.StoreTimeslotCount;
import com.flexible.booking.dto.enums.AvailabilityLevel;
import com.flexible.booking.dto.request.ReservationRequest;
import com.flexible.booking.dto.response.AvailableDatesResponse;
import com.flexible.booking.dto.response.AvailableTimeslotsResponse;
import com.flexible.booking.exception.ApiForbiddenException;
import com.flexible.booking.exception.ApiResourceNotFoundException;
import com.flexible.booking.model.Booking;
import com.flexible.booking.model.CancelBooking;
import com.flexible.booking.model.StoreTimeslot;
import com.flexible.booking.model.User;
import com.flexible.booking.model.enums.ReservationRole;
import com.flexible.booking.repository.BookingRepository;
import com.flexible.booking.repository.CancelBookingRepository;
import com.flexible.booking.repository.StoreTimeslotRepository;
import com.flexible.booking.repository.UserRepository;
import com.flexible.booking.utils.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CancelBookingRepository cancelBookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreTimeslotRepository storeTimeslotRepository;


    public AvailableDatesResponse getAvailableDates(Integer storeId, String yearMonth) {
        LocalDate startOfMonth = LocalDate.parse(yearMonth + "-01");
        LocalDate endOfMonth = startOfMonth.withDayOfMonth(startOfMonth.lengthOfMonth());
        LocalDate tomorrow = LocalDate.now().plusDays(1);

        List<StoreTimeslotCount> storeTimeslotCountList = storeTimeslotRepository.getStoreTimeslotCountByWeekday(storeId);
        List<Booking> bookingList = bookingRepository.getBookingRecordByMonth(storeId, yearMonth);

        AvailableDatesResponse response = new AvailableDatesResponse();
        response.setYearMonth(yearMonth);
        for(LocalDate date = startOfMonth; !date.isAfter(endOfMonth); date = date.plusDays(1)) {
            if(tomorrow.isAfter(date)) {
                AvailableDatesInfo adi = getDateWithNoneAvailability(date.toString());
                response.getAvailableDates().add(adi);
                continue;
            }

            DayOfWeek currWeekDay = date.getDayOfWeek();
            Optional<StoreTimeslotCount> storeTimeslotCountOpt = storeTimeslotCountList.stream()
                                                                .filter(countObj -> countObj.getWeekday().equals(currWeekDay))
                                                                .findAny();
            if(storeTimeslotCountOpt.isPresent()) {
                StoreTimeslotCount storeTimeslotCount = storeTimeslotCountOpt.get();
                final LocalDate finalDate = date;
                Long reservedTimeslot = bookingList.stream()
                                            .filter(booking -> booking.getReservationDate().isEqual(finalDate))
                                            .count();
                Long totalTimeslot = storeTimeslotCount.getNoOfTimeslot();
                Long remainingTimeslots = totalTimeslot - reservedTimeslot;
                Float occupancyRate = ((float) remainingTimeslots) / totalTimeslot;

                AvailableDatesInfo adi = new AvailableDatesInfo();
                adi.setDate(date.toString());
                adi.setRemainingTimeslots(remainingTimeslots.intValue());
                adi.setTotalTimeslots(totalTimeslot.intValue());
                adi.setAvailabilityLevel(getAvailabilityLevel(occupancyRate));

                response.getAvailableDates().add(adi);
            }
            else {
                AvailableDatesInfo adi = getDateWithNoneAvailability(date.toString());
                response.getAvailableDates().add(adi);
            }
        }

        return response;
    }

    public AvailabilityLevel getAvailabilityLevel(Float availabilityLevel) {
        if(availabilityLevel > 0.66) {
            return AvailabilityLevel.HIGH;
        }
        else if(availabilityLevel > 0.33) {
            return AvailabilityLevel.MIDDLE;
        }
        else if(availabilityLevel > 0) {
            return AvailabilityLevel.LOW;
        }
        else {
            return AvailabilityLevel.NONE;
        }
    }

    public AvailableDatesInfo getDateWithNoneAvailability(String date) {
        AvailableDatesInfo adi = new AvailableDatesInfo();
        adi.setDate(date);
        adi.setRemainingTimeslots(0);
        adi.setTotalTimeslots(0);
        adi.setAvailabilityLevel(AvailabilityLevel.NONE);
        return adi;
    }

    public AvailableTimeslotsResponse getAvailableTimeslots(Integer storeId, String date) {
        LocalDate targetDate = LocalDate.parse(date);
        DayOfWeek targetWeekDay = targetDate.getDayOfWeek();

        List<StoreTimeslot> totalTimeslotsObj = storeTimeslotRepository.findByStoreIdAndWeekday(storeId, targetWeekDay);
        List<String> availableTimeslots = totalTimeslotsObj.stream().map(t -> t.getStartTime()).collect(Collectors.toList());

        if(!CollectionUtils.isEmpty(availableTimeslots)) {
            List<Booking> reservedTimeslotsObj = bookingRepository.findByStoreIdAndReservationDate(storeId, targetDate);
            List<String> reservedTimeslots = new ArrayList<>();

            if(!CollectionUtils.isEmpty(reservedTimeslotsObj)) {
                reservedTimeslots = reservedTimeslotsObj.stream().map(b -> b.getTimeslot()).collect(Collectors.toList());
            }
            availableTimeslots.removeAll(reservedTimeslots);
        }

        AvailableTimeslotsResponse atr = new AvailableTimeslotsResponse();
        atr.setStoreId(storeId);
        atr.setDate(date);
        atr.setAvailableTimeslots(availableTimeslots);
        return atr;
    }

    public Booking reserve(ReservationRequest request) {
        Optional<Booking> bookingRecords = bookingRepository.findByStoreIdAndReservationDateAndTimeslot(request.getStoreId(),
                                                                request.getReservationDate(), request.getTimeslot());
        if(bookingRecords.isPresent()) {
            throw new ApiForbiddenException("This timeslot has already reserved.");
        }

        Optional<User> userOpt = userRepository.findByUsername(request.getUsername());
        if(!userOpt.isPresent()) {
            throw new ApiForbiddenException("User does not exist.");
        }

        Booking booking = ProjectUtils.transformFrom(request, Booking.class);
        booking.setUserId(userOpt.get().getId());
        booking.setWeekday(booking.getReservationDate().getDayOfWeek());
        booking.setReserveBy(ReservationRole.USER);
        return bookingRepository.save(booking);
    }

    @Transactional
    public void cancel(Integer bookingId) {
        Optional<Booking> bookingRecordOpt = bookingRepository.findById(bookingId);
        if(!bookingRecordOpt.isPresent()) {
            throw new ApiResourceNotFoundException("Record does not exist.");
        }
        Booking bookingRecord = bookingRecordOpt.get();
        CancelBooking cancelBooking = ProjectUtils.transformFrom(bookingRecord, CancelBooking.class);
        cancelBooking.setId(null);
        cancelBookingRepository.save(cancelBooking);
        bookingRepository.delete(bookingRecord);
    }
}
