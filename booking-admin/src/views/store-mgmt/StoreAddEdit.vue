<template>
<div>
    <CRow>
      <CCol md="9">
        <CCard>
          <CCardHeader>
            <strong>{{editMode ? 'Update' : 'Add'}} a store</strong>
          </CCardHeader>
          <CCardBody>
            <CForm>
                <CInput label="Store Name" horizontal v-model="name" maxlength="100" />
                <CRow form class="form-group">
                  <CCol tag="label" sm="3" class="col-form-label">
                    Available For Booking:
                  </CCol>
                  <CCol sm="9">
                    <CSwitch color="info" shape="pill" :checked.sync="canBook" />
                  </CCol>
                </CRow>
                <CInput label="Booking Duration (Minutes)" horizontal v-model="bookingDurationInMin" placeholder="60" maxlength="3" />
                <CInput label="Working Hour From" horizontal v-model="workingHourFrom" placeholder="09:00" maxlength="5" />
                <CInput label="Working Hour To" horizontal v-model="workingHourTo" placeholder="18:00" maxlength="5" />
                <CRow form class="form-group">
                  <CCol tag="label" sm="3" class="col-form-label">Open At</CCol>
                  <CCol sm="9" class="form-inline">
                    <CInputCheckbox label="Mon" :custom="true" :inline="true" :checked.sync="openAtMon" />
                    <CInputCheckbox label="Tue" :custom="true" :inline="true" :checked.sync="openAtTue" />
                    <CInputCheckbox label="Wed" :custom="true" :inline="true" :checked.sync="openAtWed" />
                    <CInputCheckbox label="Thu" :custom="true" :inline="true" :checked.sync="openAtThu" />
                    <CInputCheckbox label="Fri" :custom="true" :inline="true" :checked.sync="openAtFri" />
                    <CInputCheckbox label="Sat" :custom="true" :inline="true" :checked.sync="openAtSat" />
                    <CInputCheckbox label="Sun" :custom="true" :inline="true" :checked.sync="openAtSun" />
                  </CCol>
                </CRow>
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
import storeService from '../../api/store.service.js';
import helper from '../../api/helper.js';

export default {
    data() {
        return {
          id: null,
          name: null,
          canBook: true,
          bookingDurationInMin: null,
          workingHourFrom: null,
          workingHourTo: null,
          openAtMon: true,
          openAtTue: true,
          openAtWed: true,
          openAtThu: true,
          openAtFri: true,
          openAtSat: true,
          openAtSun: true,
          editMode: false,
        }
    },
    methods: {
        submitForm() {
          if(this.editMode) {
            storeService.update(this.$data).then(() => {
              alert("Store updated successfully.");
              this.$router.push('/');
            }).catch(err => helper.apiErrorHandling(err, this.$router));
          }
          else {
            storeService.create(this.$data).then(() => {
              alert("Store created successfully.");
              this.$router.push('/');
            }).catch(err => helper.apiErrorHandling(err, this.$router));
          }
        },
        getStoreById(id) {
            storeService.get(id).then(result => {
                this.name = result.data.name;
                this.canBook = result.data.canBook;
                this.bookingDurationInMin = result.data.bookingDurationInMin;
                this.workingHourFrom = this.convertToTime(result.data.workingHourFrom);
                this.workingHourTo = this.convertToTime(result.data.workingHourTo);
                this.openAtMon = result.data.openAtMon;
                this.openAtTue = result.data.openAtTue;
                this.openAtWed = result.data.openAtWed;
                this.openAtThu = result.data.openAtThu;
                this.openAtFri = result.data.openAtFri;
                this.openAtSat = result.data.openAtSat;
                this.openAtSun = result.data.openAtSun;
            }).catch(err => helper.apiErrorHandling(err, this.$router));
        },
        convertToTime(hour) {
          if(typeof hour !== 'number') {
            return "";
          }
          if(hour >= 10) {
            return hour + ":00";
          }
          else {
            return "0" + hour + ":00";
          }
        }
    },
    created() {
        if (this.$route.query.id) {
            this.editMode = true;
            this.id = this.$route.query.id;
            this.getStoreById(this.$route.query.id);
        }
        else {
            this.editMode = false;
            this.id = null;
        }
    }
}
</script>