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
        return new Promise ((resolve, reject) => {
            axios.post(process.env.VUE_APP_API_ENDPOINT + "/booking/reserve", 
            reserveObj,
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
    cancel(bookingId) {
        return new Promise ((resolve, reject) => {
            axios.delete(process.env.VUE_APP_API_ENDPOINT + "/booking/cancel/" + bookingId, 
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
    getBookingHistory(userObj) {
        return new Promise ((resolve, reject) => {
            axios.post(process.env.VUE_APP_API_ENDPOINT + "/booking/booking-history", 
            userObj,
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