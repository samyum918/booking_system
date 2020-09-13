<template>
<div>
  <CCard>
    <CCardHeader>
      <slot name="header">
        <CIcon name="cil-grid"/> Store Timeslots Table
        <div class="card-header-actions">
            <a href="/#/store-timeslot/add-edit">
                <CButton type="button" size="sm" color="success">
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
                    <CButton color="info" size="sm" class="mr-1" @click="editItem(item.id)">Edit</CButton>
                    <CButton color="danger" size="sm" @click="deleteItem(item)">Delete</CButton>
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
            fields: ['id', 'storeName', 'weekday', 'startTime', 'endTime', 
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
            }).catch(err => helper.apiErrorHandling(err, this.$router));
        },
        editItem(id) {
            this.$router.push("/store-timeslot/add-edit?id=" + id);
        },
        deleteItem(item) {
            if(confirm("Are you sure to delete the record?")) {
                let itemIndex = this.$data.items.indexOf(item);
                this.$data.items.splice(itemIndex, 1);

                storeTimeslotService.delete(item.id).then(result => {
                    alert("Deleted with success.");
                }).catch(err => {
                    this.$data.items.splice(itemIndex, 0, item);
                    helper.apiErrorHandling(err, this.$router); 
                });
            }
        }
    },
    created() {
        this.getStoreTimeslots();
    }
}
</script>