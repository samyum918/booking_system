import dataService from './data.service';

export default {
    getAll() {
        return dataService.getAll("/store-timeslot/get");
    },
    get(id) {
        return dataService.getOne("/store-timeslot/get", id);
    },
    create(storeTimeslotObj) {
        return dataService.post("/store-timeslot/create", storeTimeslotObj);
    },
    update(storeTimeslotObj) {
        return dataService.post("/store-timeslot/update", storeTimeslotObj);
    },
    delete(id) {
        return dataService.delete("/store-timeslot/delete", id);
    }
}