import auth from './auth';
import axios from 'axios';

export default {
    getByPage(page, limit) {
        return new Promise ((resolve, reject) => {
            axios.get(process.env.VUE_APP_API_ENDPOINT + "/store-timeslot/get",
            {
                params: {page, limit},
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