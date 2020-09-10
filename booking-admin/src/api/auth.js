export default {
    apiHeader() {
        let header = {};

        let adminUser = JSON.parse(localStorage.getItem('admin-user'));
        if (adminUser && adminUser.jwt) {
            header["Authorization"] = 'Bearer ' + adminUser.jwt;
        }

        return header;
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