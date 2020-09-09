export default {
    apiHeader() {
        // return authorization header with jwt token
        let adminUser = JSON.parse(localStorage.getItem('admin-user'));

        if (adminUser && adminUser.jwt) {
            return { 'Authorization': 'Bearer ' + adminUser.jwt };
        } else {
            return {};
        }
    },
    isLoggedIn() {
        if(localStorage.getItem('admin-user')) {
            return true;
        }
        return false;
    },
    getAdminUserObj() {
        return JSON.parse(localStorage.getItem('admin-user'));
    },
    getAdminUsername() {
        let adminUser = JSON.parse(localStorage.getItem('admin-user'));
        if (adminUser && adminUser.username) {
            return adminUser.username;
        }
        return null;
    }
}