export default {
    apiHeader() {
        // return authorization header with jwt token
        let user = JSON.parse(localStorage.getItem('user'));

        if (user && user.jwt) {
            return { 'Authorization': 'Bearer ' + user.jwt };
        } else {
            return {};
        }
    },
    isLoggedIn() {
        if(localStorage.getItem('user')) {
            return true;
        }
        return false;
    },
    getUsername() {
        let user = JSON.parse(localStorage.getItem('user'));
        if (user && user.username) {
            return user.username;
        }
        return null;
    }
}