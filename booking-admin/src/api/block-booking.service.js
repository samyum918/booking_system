import dataService from './data.service';

export default {
    date(dateObj) {
        return dataService.post("/block-booking/date", dateObj);
    },
    timeslot(timeslotObj) {
        return dataService.post("/block-booking/timeslot", timeslotObj);
    },
}