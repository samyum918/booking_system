<template>
<div>
  <CCard>
    <CCardHeader>
      <slot name="header">
        <CIcon name="cil-grid"/> Timeslot Blocking Records Table
      </slot>
    </CCardHeader>
    <CCardBody>
        <CDataTable 
        caption="Timeslot Blocking Records List" 
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
            fields: ['id', 'storeId', 'date', 'timeslot'],
            loading: false,
            pagination: {dots: false},
        }
    },
    methods: {
        getTimeslotBlockingRecords() {
            this.loading = true;

            recordsService.timeslotBlocking().then(result => {
                this.items = result.data;
                this.loading = false;
            }).catch(err => helper.apiErrorHandling(err, this.$router));
        },
    },
    created() {
        this.getTimeslotBlockingRecords();
    }
}
</script>