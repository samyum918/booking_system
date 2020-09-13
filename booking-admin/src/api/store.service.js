import dataService from './data.service';

export default {
    getAll() {
        return dataService.getAll("/store/get");
    },
    get(id) {
        return dataService.getOne("/store/get", id);
    },
    create(storeObj) {
        return dataService.post("/store/create", storeObj);
    },
    update(storeObj) {
        return dataService.post("/store/update", storeObj);
    },
    delete(id) {
        return dataService.delete("/store/delete", id);
    }
}