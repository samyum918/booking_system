import auth from './auth';
import axios from 'axios';

export default {
    getAll(uri) {
        return new Promise ((resolve, reject) => {
            axios.get(process.env.VUE_APP_API_ENDPOINT + uri,
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
    getOne(uri, id) {
        return new Promise ((resolve, reject) => {
            axios.get(process.env.VUE_APP_API_ENDPOINT + uri + "/" + id,
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
    post(uri, dataObj) {
        return new Promise ((resolve, reject) => {
            axios.post(process.env.VUE_APP_API_ENDPOINT + uri,
            dataObj,
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
    delete(uri, id) {
        return new Promise ((resolve, reject) => {
            axios.delete(process.env.VUE_APP_API_ENDPOINT + uri + "/" + id,
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