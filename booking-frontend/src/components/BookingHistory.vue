<template>
    <div class="vue-tempalte">
        <form>
            <h3>Booking History</h3>
            <table class="table">
            <thead>
                <tr>
                <th scope="col">#</th>
                <th scope="col">Store Name</th>
                <th scope="col">Reservation Date</th>
                <th scope="col">Timeslot</th>
                <th scope="col">Action</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(bookingHistory, itemKey) in bookingHistoryList" :key="bookingHistory.id">
                    <th scope="row">{{++itemKey}}</th>
                    <td>{{bookingHistory.storeName}}</td>
                    <td>{{bookingHistory.reservationDate}}</td>
                    <td>{{bookingHistory.timeslot}}</td>
                    <td>
                        <button v-if="bookingHistory.canCancel" @click="cancelBooking(bookingHistory)" type="button" class="btn btn-danger">
                            Cancel
                        </button>
                    </td>
                </tr>
            </tbody>
            </table>
        </form>
    </div>
</template>

<script>
    import helper from '../api/helper.js';
    import bookingService from '../api/booking.service.js';

    export default {
        data() {
            return {
                bookingHistoryList: null
            }
        },
        methods: {
            cancelBooking(bookingHistory) {
                if(confirm("Are you sure to cancel the booking?")) {
                    let bookingId = bookingHistory.bookingId;
                    let bookingHistoryIndex = this.$data.bookingHistoryList.indexOf(bookingHistory);
                    this.$data.bookingHistoryList.splice(bookingHistoryIndex, 1);

                    bookingService.cancel(bookingId)
                    .then(alert("Cancelled with success."))
                    .catch(err => {
                        this.$data.bookingHistoryList.splice(bookingHistoryIndex, 0, bookingHistory);
                        helper.apiErrorHandling(err, this.$router);
                    });
                }
            },
            getBookingHistory() {
                let user = JSON.parse(localStorage.getItem('user'));
                let username;
                if(user && user.username) {
                    username = user.username;
                }
                bookingService.getBookingHistory({username}).then(resp => {
                    this.bookingHistoryList = resp.data;
                }).catch(err => helper.apiErrorHandling(err, this.$router));
            },
        },
        created() {
            this.getBookingHistory();
        }
    }
</script>