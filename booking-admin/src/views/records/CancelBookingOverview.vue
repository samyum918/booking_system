<template>
<div>
  <CCard>
    <CCardHeader>
      <slot name="header">
        <CIcon name="cil-grid"/> Cancel Booking Records Table
      </slot>
    </CCardHeader>
    <CCardBody>
        <CDataTable 
        caption="Cancel Booking Records List" 
        :items="items" 
        :fields="fields" 
        items-per-page-select
        column-filter
        :loading="loading" 
        :pagination="pagination"
        >
        </CDataTable>
    </CCardBody>
  </CCard>
</div>
</template>
<script>
import recordsService from '../../api/records.service';
import helper from '../../api/helper.js';

export default {
    data() {
        return {
            items: [],
            fields: ['id', 'storeName', 'firstName', 'lastName', 'username', 'reservationDate', 'timeslot'],
            loading: false,
            pagination: {dots: false},
        }
    },
    methods: {
        getCancelBookingRecords() {
            this.loading = true;

            recordsService.cancelBooking().then(result => {
                this.items = result.data;
                this.loading = false;
            }).catch(err => helper.apiErrorHandling(err, this.$router));
        },
    },
    created() {
        this.getCancelBookingRecords();
    }
}
</script>