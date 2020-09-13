<template>
<div>
  <CCard>
    <CCardHeader>
      <slot name="header">
        <CIcon name="cil-grid"/> User Records Table
      </slot>
    </CCardHeader>
    <CCardBody>
        <CDataTable 
        caption="User Records List" 
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
            fields: ['id', 'firstName', 'lastName', 'username', 'phone', 'gender', 'email'],
            loading: false,
            pagination: {dots: false},
        }
    },
    methods: {
        getUserRecords() {
            this.loading = true;

            recordsService.user().then(result => {
                this.items = result.data;
                this.loading = false;
            }).catch(err => helper.apiErrorHandling(err, this.$router));
        },
        cancelItem(id) {

        },
    },
    created() {
        this.getUserRecords();
    }
}
</script>