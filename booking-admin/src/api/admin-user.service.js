import store from '../store';
import axios from 'axios';

export default {
    login(username, password) {
        return new Promise ((resolve, reject) => {
            axios.post(process.env.VUE_APP_API_ENDPOINT + "/login", {
                username,
                password
            }).then(resp => {
                const adminUser = resp.data;
                localStorage.setItem('admin-user', JSON.stringify(adminUser));
                store.commit('login', adminUser);
                resolve(resp);
            })
            .catch(err => {
                localStorage.removeItem('admin-user');
                reject(err.response);
            })
        });
    },
    logout() {
        localStorage.removeItem('admin-user');
        store.commit('logout');
    },
    register(userObj) {
        const {username, password} = userObj;
        return new Promise ((resolve, reject) => {
            axios.post(process.env.VUE_APP_API_ENDPOINT + "/user/register", {
                username,
                password
            }).then(resp => {
                resolve(resp);
            })
            .catch(err => {
                reject(err.response);
            })
        });
    }
};

