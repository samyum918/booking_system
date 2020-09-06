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
        let user = localStorage.getItem('user');
        return typeof(user) !== "undefined" && user !== null;
    },
    getUsername() {
        let user = JSON.parse(localStorage.getItem('user'));
        if (user && user.username) {
            return user.username;
        }
        return null;
    }
}