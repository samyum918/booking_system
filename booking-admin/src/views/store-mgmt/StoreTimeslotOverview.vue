<template>
<div>
  <CCard>
    <CCardHeader>
      <slot name="header">
        <CIcon name="cil-grid"/> Store Timeslots List
        <div class="card-header-actions">
            <a href="/#/store/creation">
                <CButton type="button" size="sm" color="danger">
                    <CIcon name="cil-plus"/> Add new
                </CButton>
            </a>
        </div>
      </slot>
    </CCardHeader>
    <CCardBody>
        <CDataTable caption="Store Timeslots List" :items="items" :fields="fields" :itemsPerPage="10" :loading="loading" pagination></CDataTable>
    </CCardBody>
  </CCard>
</div>
</template>
<script>
import storeTimeslotService from '../../api/store-timeslot.service';
import helper from '../../api/helper.js';

export default {
    data() {
        return {
            items: [],
            fields: ['id', 'storeId', 'weekday', 'startTime', 'endTime'],
            pagination: {activePage: 1, pages: 10},
            loading: false,
        }
    },
    methods: {
        getStoreTimeslots() {
            this.$data.loading = true;
            const pagination = this.$data.pagination;
            const page = pagination.activePage;
            const limit = 10;

            storeTimeslotService.getByPage(page, limit).then(result => {
                this.$data.items = result.data.content;
                pagination.pages = result.data.totalPages;
                this.$data.loading = false;
            }).catch(err => helper.apiErrorHandling(err));
        }
    },
    created() {
        this.getStoreTimeslots();
    }
}
</script>