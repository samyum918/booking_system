<template>
<div>
  <CCard>
    <CCardHeader>
      <slot name="header">
        <CIcon name="cil-grid"/> Booking Records Table
      </slot>
    </CCardHeader>
    <CCardBody>
        <CDataTable 
        caption="Booking Records List" 
        :items="items" 
        :fields="fields" 
        items-per-page-select
        column-filter
        :loading="loading" 
        :pagination="pagination"
        >
            <template #actions="{item}">
                <td class="py-2">
                    <CButton color="danger" size="sm" v-if="new Date(item.reservationDate) > new Date()" @click="cancelItem(item.id)">Cancel</CButton>
                </td>
            </template>
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
            fields: ['id', 'storeName', 'firstName', 'lastName', 'username', 'reservationDate', 'timeslot',
                    {key: 'actions', label: '', _style: 'width:8%', sorter: false, filter: false}],
            loading: false,
            pagination: {dots: false},
        }
    },
    methods: {
        getBookingRecords() {
            this.$data.loading = true;

            recordsService.booking().then(result => {
                this.$data.items = result.data;
                this.$data.loading = false;
            }).catch(err => helper.apiErrorHandling(err, this.$router));
        },
        cancelItem(id) {

        },
    },
    created() {
        this.getBookingRecords();
    }
}
</script>