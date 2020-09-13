import dataService from './data.service';

export default {
    booking() {
        return dataService.getAll("/records/booking");
    },
    cancelBooking() {
        return dataService.getAll("/records/cancel-booking");
    },
    user() {
        return dataService.getAll("/records/user");
    },
    dateBlocking() {
        return dataService.getAll("/records/date-blocking");
    },
    timeslotBlocking() {
        return dataService.getAll("/records/timeslot-blocking");
    },
}