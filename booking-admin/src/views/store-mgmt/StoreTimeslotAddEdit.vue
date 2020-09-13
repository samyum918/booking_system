<template>
<div>
    <CRow>
      <CCol md="9">
        <CCard>
          <CCardHeader>
            <strong>{{editMode ? 'Update' : 'Add'}} a store timeslot</strong>
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
                <CIcon name="cil-check-circle"/> {{editMode ? 'Update' : 'Submit'}}
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
            id: null,
            storeId: null,
            weekday: null,
            startTime: null,
            endTime: null,
            editMode: false,
        }
    },
    methods: {
        getStores() {
            storeService.getAll().then(result => {
                this.items = result.data;
                this.storeOptions = this.items.map(item => {return {'label': item.name, 'value': item.id}});
                this.storeOptions.splice(0, 0, {'label': '---', 'value': ''});
            }).catch(err => helper.apiErrorHandling(err, this.$router));
        },
        getStoreTimeslotById(id) {
            storeTimeslotService.get(id).then(result => {
                this.storeId = result.data.storeId;
                this.weekday = result.data.weekday;
                this.startTime = result.data.startTime;
                this.endTime = result.data.endTime;
            }).catch(err => helper.apiErrorHandling(err, this.$router));
        },
        submitForm() {
            const storeTimeslotObj = {
                id: this.id,
                storeId: this.storeId,
                weekday: this.weekday,
                startTime: this.startTime,
                endTime: this.endTime,
            }
            if(this.editMode) {
                storeTimeslotService.update(storeTimeslotObj).then(result => {
                    alert("Updated a timeslot.");
                    this.$router.push("/store-timeslot/overview");
                }).catch(err => helper.apiErrorHandling(err, this.$router));
            }
            else {
                storeTimeslotService.create(storeTimeslotObj).then(result => {
                    alert("Created a timeslot.");
                    this.$router.push("/store-timeslot/overview");
                }).catch(err => helper.apiErrorHandling(err, this.$router));
            }
        }
    },
    created() {
        this.getStores();
        if (this.$route.query.id) {
            this.editMode = true;
            this.id = this.$route.query.id;
            this.getStoreTimeslotById(this.$route.query.id);
        }
        else {
            this.editMode = false;
            this.id = null;
        }
    }
}
</script>