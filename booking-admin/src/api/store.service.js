import auth from './auth';
import axios from 'axios';

export default {
    create(storeObj) {
        return new Promise ((resolve, reject) => {
            axios.post(process.env.VUE_APP_API_ENDPOINT + "/store/create",
            storeObj,
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