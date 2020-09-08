<template>
    <div class="vue-tempalte">
        <form>
            <h3>Book a service</h3>
            <div class="form-group">
                <label>Store</label>
                <select v-model="selectedStoreId" @change="storeIdSelected($event)" class="form-control form-control-lg">
                    <option value="">---</option>
                    <option v-for="store in stores" :key="store.id" :value="store.id">{{store.name}}</option>
                </select>
            </div>
            <div class="form-group">
                <label>Available Date</label>
                <v-calendar :attributes="availableDatesAttrs" :disabled-dates="disabledDates" 
                            @update:from-page="switchYearMonth($event)" @dayclick="dateSelected($event)" style="margin: 1rem auto;" />
            </div>

            <div class="form-group">
                <label>Timeslot</label>
                <select v-model="selectedTimeslot" class="form-control form-control-lg">
                    <option>---</option>
                    <option v-for="timeslot in availableTimeslots" :key="timeslot" :value="timeslot">{{timeslot}}</option>
                </select>
            </div>

            <button type="button" @click="reserve()" class="btn btn-dark btn-lg btn-block btn-margin">Submit</button>
        </form>
    </div>
</template>

<script>
    import helper from '../api/helper.js';
    import bookingService from '../api/booking.service.js';
    import Calendar from 'v-calendar/lib/components/calendar.umd';

    export default {
        components: {
            'v-calendar': Calendar
        },
        data() {
            return {
                stores: null,
                availableTimeslots: null,
                selectedStoreId: null,
                currYearMonth: null,
                selectedDate: null,
                selectedTimeslot: null,
                availableDatesAttrs: [],
                disabledDates: [
                    { weekdays: [1, 2, 3, 4, 5, 6, 7] }
                ],
            };
        },
        methods: {
            storeIdSelected(event) {
                const storeId = event.target.value;
                if(storeId) {
                    if(!this.currYearMonth) {
                        this.getAvailableDates(storeId, this.getTmrYearMonth());
                    }
                    else {
                        this.getAvailableDates(storeId, this.currYearMonth);
                    }
                }
                else {
                    this.disableAllDates();
                }
            },
            switchYearMonth(event) {
                let year = event.year;
                let month = event.month;
                const yearMonth = this.getYearMonth(year, month);
                this.currYearMonth = yearMonth;

                if(this.selectedStoreId) {
                    if(yearMonth >= this.getTmrYearMonth()) {
                        this.getAvailableDates(this.selectedStoreId, yearMonth);
                    }
                    else {
                        this.disableAllDates();
                    }
                }
            },
            dateSelected(event) {
                const storeId = this.selectedStoreId;
                const date = event.id;
                if(storeId && date) {
                    this.selectedDate = date;
                    this.getAvailableTimeslots(storeId, date);
                }
            },
            getTmrYearMonth() {
                let tmr = new Date();
                tmr.setDate(tmr.getDate() + 1);
                let year = tmr.getFullYear();
                let month = tmr.getMonth() + 1;
                return this.getYearMonth(year, month);
            },
            getYearMonth(year, month) {
                if(month < 10) {
                    month = '0' + month;
                }
                return year + "-" + month;
            },
            disableAllDates() {
                this.availableDatesAttrs = [];
                this.disabledDates = [{ weekdays: [1, 2, 3, 4, 5, 6, 7] }];
            },
            getAvailableStores() {
                bookingService.getAvailableStores().then(resp => {
                    this.stores = resp.data;
                }).catch(err => helper.apiErrorHandling(err, this.$router));
            },
            getAvailableDates(store_id, year_month) {
                this.disableAllDates();

                bookingService.getAvailableDates(store_id, year_month).then(resp => {
                    let disabledDates = [];
                    let highAvailability = {
                        key: 'highAvailability',
                        dot: {
                            color: "green"
                        },
                        dates: [],
                    };
                    let middleAvailability = {
                        key: 'middleAvailability',
                        dot: {
                            color: "yellow"
                        },
                        dates: [],
                    };
                    let lowAvailability = {
                        key: 'lowAvailability',
                        dot: {
                            color: "red"
                        },
                        dates: [],
                    };
                    resp.data.availableDates.forEach(item => {
                        if(item.remainingTimeslots <= 0) {
                            disabledDates.push(new Date(item.date));
                        }
                        if(item.availabilityLevel == "HIGH") {
                            highAvailability.dates.push(new Date(item.date));
                        }
                        else if(item.availabilityLevel == "MIDDLE") {
                            middleAvailability.dates.push(new Date(item.date));
                        }
                        else if(item.availabilityLevel == "LOW") {
                            lowAvailability.dates.push(new Date(item.date));
                        }
                    });
                    this.disabledDates = disabledDates;
                    this.availableDatesAttrs.push(highAvailability);
                    this.availableDatesAttrs.push(middleAvailability);
                    this.availableDatesAttrs.push(lowAvailability);
                }).catch(err => helper.apiErrorHandling(err, this.$router));
            },
            getAvailableTimeslots(store_id, date) {
                bookingService.getAvailableTimeslots(store_id, date).then(resp => {
                    this.availableTimeslots = resp.data.availableTimeslots;
                }).catch(err => helper.apiErrorHandling(err, this.$router));
            },
            reserve() {
                let user = JSON.parse(localStorage.getItem('user'));
                let username;
                if(user && user.username) {
                    username = user.username;
                }
                const storeId = this.selectedStoreId;
                const reservationDate = this.selectedDate;
                const timeslot = this.selectedTimeslot;

                bookingService.reserve({username, storeId, reservationDate, timeslot}).then(() => {
                    alert("Reserved successfully.");
                    this.$router.push('/thankyou');
                }).catch(err => helper.apiErrorHandling(err, this.$router));
            }
        },
        created() {
            this.getAvailableStores();
        }
    }
</script>