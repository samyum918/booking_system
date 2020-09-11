<template>
<div>
    <CRow>
      <CCol md="9">
        <CCard>
          <CCardHeader>
            <strong>Store Timeslot Form</strong>
          </CCardHeader>
          <CCardBody>
            <CForm>
                <CSelect
                label="Store"
                horizontal
                :options="storeOptions" 
                :value.sync="storeId"
                />
                <CSelect
                label="Weekday"
                horizontal
                :options="weekdayOptions" 
                :value.sync="weekday"
                />
                <CInput label="Start Time" horizontal v-model="startTime" placeholder="09:00" maxlength="5" />
                <CInput label="End Time" horizontal v-model="endTime" placeholder="10:00" maxlength="5" />
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
import storeTimeslotService from '../../api/store-timeslot.service';
import helper from '../../api/helper.js';

export default {
    data() {
        return {
            items: [],
            storeOptions: [{'label': '---', 'value': ''}],
            weekdayOptions: ['MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY'],
            storeId: null,
            weekday: null,
            startTime: null,
            endTime: null,
        }
    },
    methods: {
        getStores() {
            storeService.getAll().then(result => {
                this.$data.items = result.data;
                this.$data.storeOptions = this.$data.items.map(item => {return {'label': item.name, 'value': item.id}});
                this.$data.storeOptions.splice(0, 0, {'label': '---', 'value': ''});
            }).catch(err => helper.apiErrorHandling(err, this.$router));
        },
        submitForm() {
            const storeTimeslotObj = {
                storeId: this.$data.storeId,
                weekday: this.$data.weekday,
                startTime: this.$data.startTime,
                endTime: this.$data.endTime,
            }
            storeTimeslotService.create(storeTimeslotObj).then(result => {
                alert("Created a timeslot.");
            }).catch(err => helper.apiErrorHandling(err, this.$router));
        }
    },
    created() {
        this.getStores();
    }
}
</script>