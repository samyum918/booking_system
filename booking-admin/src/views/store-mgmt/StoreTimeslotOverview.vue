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
        <CDataTable 
        caption="Store Timeslots List" 
        :items="items" 
        :fields="fields" 
        items-per-page-select
        column-filter
        :loading="loading" 
        :pagination="pagination"
        >
            <template #actions="{item}">
                <td class="py-2">
                    <CButton color="info" size="sm" class="mr-1" @click="edit(item.id)">Edit</CButton>
                    <CButton color="danger" size="sm">Delete</CButton>
                </td>
            </template>
        </CDataTable>
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
            fields: ['id', 'storeId', 'weekday', 'startTime', 'endTime', 
                    {key: 'actions', label: '', _style: 'width:8%', sorter: false, filter: false}],
            loading: false,
            pagination: {dots: false},
        }
    },
    methods: {
        getStoreTimeslots() {
            this.$data.loading = true;

            storeTimeslotService.getAll().then(result => {
                this.$data.items = result.data;
                this.$data.loading = false;
            }).catch(err => helper.apiErrorHandling(err));
        },
        edit(id) {
            console.log(id);
        }
    },
    created() {
        this.getStoreTimeslots();
    }
}
</script>