<template>
<div>
    <CRow>
      <CCol md="9">
        <CCard>
          <CCardHeader>
            <strong>Date blocking Form</strong>
          </CCardHeader>
          <CCardBody>
            <CForm>
                <CSelect
                label="Store"
                horizontal
                :options="options" 
                :value.sync="storeId"
                />
                <CInput label="Date" horizontal v-model="date" placeholder="2020-01-01" maxlength="10" />
            </CForm>
          </CCardBody>
          <CCardFooter>
            <CButton type="submit" size="sm" color="primary" @click="submitForm()">
                <CIcon name="cil-check-circle"/> Submit
            </CButton>
          </CCardFooter>
        </CCard>
      </CCol>
    </CRow>
</div>
</template>
<script>
import storeService from '../../api/store.service';
import blockBookingService from '../../api/block-booking.service';
import helper from '../../api/helper.js';

export default {
    data() {
        return {
            items: [],
            options: [{'label': '---', 'value': ''}],
            storeId: null,
            date: null,
        }
    },
    methods: {
        getStores() {
            storeService.getAll().then(result => {
                this.items = result.data;
                this.options = this.items.map(item => {return {'label': item.name, 'value': item.id}});
                this.options.splice(0, 0, {'label': '---', 'value': ''});
            }).catch(err => helper.apiErrorHandling(err, this.$router));
        },
        submitForm() {
            const dateObj = {
                storeId: this.storeId,
                date: this.date,
            }
            blockBookingService.date(dateObj).then(result => {
                alert("Blocked a date.");
                this.$router.push("/records/date-blocking");
            }).catch(err => helper.apiErrorHandling(err, this.$router));
        }
    },
    created() {
        this.getStores();
    }
}
</script>