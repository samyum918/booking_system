<template>
<div>
  <CCard>
    <CCardHeader>
      <slot name="header">
        <CIcon name="cil-grid"/> Date Blocking Records Table
      </slot>
    </CCardHeader>
    <CCardBody>
        <CDataTable 
        caption="Date Blocking Records List" 
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
            fields: ['id', 'storeName', 'date'],
            loading: false,
            pagination: {dots: false},
        }
    },
    methods: {
        getDateBlockingRecords() {
            this.loading = true;

            recordsService.dateBlocking().then(result => {
                this.items = result.data.map(d => ({
                  "id": d.id, 
                  "storeName": d.store.name, 
                  "date": d.date
                }));
                this.loading = false;
            }).catch(err => helper.apiErrorHandling(err, this.$router));
        },
    },
    created() {
        this.getDateBlockingRecords();
    }
}
</script>