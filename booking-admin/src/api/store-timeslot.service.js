import auth from './auth';
import axios from 'axios';

export default {
    getAll() {
        return new Promise ((resolve, reject) => {
            axios.get(process.env.VUE_APP_API_ENDPOINT + "/store-timeslot/get",
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
    create(storeTimeslotObj) {
        return new Promise ((resolve, reject) => {
            axios.get(process.env.VUE_APP_API_ENDPOINT + "/store-timeslot/create",
            storeTimeslotObj,
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
    delete(id) {
        return new Promise ((resolve, reject) => {
            axios.delete(process.env.VUE_APP_API_ENDPOINT + "/store-timeslot/delete/" + id,
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