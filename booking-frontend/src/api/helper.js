import userService from '../api/user.service.js';

export default {
    apiErrorHandling(err, router) {
        if(err.status === 401) {
            alert(err.data.error);
            userService.logout();
            router.push('/login');
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