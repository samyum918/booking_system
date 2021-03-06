import axios from 'axios';
import EventBus from '../event-bus';

export default {
    login(username, password) {
        return new Promise ((resolve, reject) => {
            axios.post(process.env.VUE_APP_API_ENDPOINT + "/login", {
                username,
                password
            }).then(resp => {
                const user = resp.data;
                localStorage.setItem('user', JSON.stringify(user));
                EventBus.$emit('logged', 'User logged');
                resolve(resp);
            })
            .catch(err => {
                localStorage.removeItem('user');
                reject(err.response);
            })
        });
    },
    logout() {
        localStorage.removeItem('user');
        EventBus.$emit('logout', 'User logout');
    },
    register(userObj) {
        const {username, password, firstName, lastName, gender, email, phone} = userObj;
        return new Promise ((resolve, reject) => {
            axios.post(process.env.VUE_APP_API_ENDPOINT + "/user/register", {
                username,
                password,
                firstName,
                lastName,
                gender,
                email,
                phone
            }).then(resp => {
                resolve(resp);
            })
            .catch(err => {
                reject(err.response);
            })
        });
    }
};

