export default {
    apiErrorHandling(err) {
        this.unexpectedErrorHandling(err);
    },
    unexpectedErrorHandling(err) {
        alert("An unexpected error occured.");
        console.log(err);
    }
}