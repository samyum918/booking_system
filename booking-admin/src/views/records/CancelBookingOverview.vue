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
            fields: ['id', 'storeId', 'reservationDate', 'timeslot',
                    {key: 'actions', label: '', _style: 'width:8%', sorter: false, filter: false}],
            loading: false,
            pagination: {dots: false},
        }
    },
    methods: {
        getCancelBookingRecords() {
            this.$data.loading = true;

            recordsService.cancelBooking().then(result => {
                this.$data.items = result.data;
                this.$data.loading = false;
            }).catch(err => helper.apiErrorHandling(err));
        },
        cancelItem(id) {

        },
    },
    created() {
        this.getCancelBookingRecords();
    }
}
</script>