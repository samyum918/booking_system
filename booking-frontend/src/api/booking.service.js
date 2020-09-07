import auth from './auth';
import axios from 'axios';

export default {
    getAvailableStores() {
        return new Promise ((resolve, reject) => {
            axios.get(process.env.VUE_APP_API_ENDPOINT + "/booking/available-stores",
            {
                headers: auth.apiHeader()
            }).then(resp => {
                resolve(resp);
            })
            .catch(err => {
                reject(err.response);
            });
        });
    },
    getAvailableDates(store_id, year_month) {
        return new Promise ((resolve, reject) => {
            axios.get(process.env.VUE_APP_API_ENDPOINT + "/booking/available-dates", 
            {
                params: {store_id, year_month},
                headers: auth.apiHeader()
            }).then(resp => {
                resolve(resp);
            })
            .catch(err => {
                reject(err.response);
            });
        });
    },
    getAvailableTimeslots(store_id, date) {
        return new Promise ((resolve, reject) => {
            axios.get(process.env.VUE_APP_API_ENDPOINT + "/booking/available-timeslots", 
            {
                params: {store_id, date},
                headers: auth.apiHeader()
            }).then(resp => {
                resolve(resp);
            })
            .catch(err => {
                reject(err.response);
            });
        });
    },
    reserve(reserveObj) {
        const {username, storeId, reservationDate, timeslot} = reserveObj;
        return new Promise ((resolve, reject) => {
            axios.post("http://localhost:8091/booking/reserve", 
            {
                username, 
                storeId, 
                reservationDate, 
                timeslot
            },
            {
                headers: auth.apiHeader()
            }).then(resp => {
                resolve(resp);
            })
            .catch(err => {
                reject(err.response);
            });
        });
    }
}