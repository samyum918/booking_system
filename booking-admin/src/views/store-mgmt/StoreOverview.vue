<template>
<div>
  <CCard>
    <CCardHeader>
      <slot name="header">
        <CIcon name="cil-grid"/> Stores List
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
        <CDataTable :items="items" caption="Stores List" pagination></CDataTable>
    </CCardBody>
  </CCard>
</div>
</template>
<script>
import storeService from '../../api/store.service';
import helper from '../../api/helper.js';

export default {
    data() {
        return {
            items: [],
            pagination: {activePage: 1, pages: 10, size: 'lg'}
        }
    },
    methods: {
        getStores() {
            const pagination = this.$data.pagination;
            const page = pagination.activePage;
            const limit = 10;
            if(pagination.size == 'sm') {
                limit = 5;
            }
            storeService.getByPage(page, limit).then(result => {
                this.$data.items = result.data.content;
                pagination.pages = result.data.totalPages;
                console.log(result);
            }).catch(err => helper.apiErrorHandling(err));
        }
    },
    created() {
        this.getStores();
    }
}
</script>