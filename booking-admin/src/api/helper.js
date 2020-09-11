import adminUserService from '../api/admin-user.service.js';

export default {
    apiErrorHandling(err, router) {
        if(err.status === 401) {
            alert(err.data.error);
            adminUserService.logout();
            router.push('/pages/login');
        }
        else {
            this.unexpectedErrorHandling(err);
        }
    },
    unexpectedErrorHandling(err) {
        alert("An unexpected error occured.");
        console.log(err);
    }
}