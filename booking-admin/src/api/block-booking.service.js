import auth from './auth';
import axios from 'axios';

export default {
    date(dateObj) {
        return new Promise ((resolve, reject) => {
            axios.post(process.env.VUE_APP_API_ENDPOINT + "/block-booking/date",
            dateObj,
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
    timeslot(timeslotObj) {
        return new Promise ((resolve, reject) => {
            axios.post(process.env.VUE_APP_API_ENDPOINT + "/block-booking/timeslot",
            timeslotObj,
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
}