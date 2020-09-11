<template>
<div>
    <CRow>
      <CCol md="9">
        <CCard>
          <CCardHeader>
            <strong>Store Management Form</strong>
          </CCardHeader>
          <CCardBody>
            <CForm>
                <CInput label="Store Name" horizontal v-model="name" maxlength="100" />
                <CRow form class="form-group">
                  <CCol tag="label" sm="3" class="col-form-label">
                    Available For Booking:
                  </CCol>
                  <CCol sm="9">
                    <CSwitch color="info" shape="pill" v-model="canBook" :checked="true" @update:checked="canBook=$event" />
                  </CCol>
                </CRow>
                <CInput label="Booking Duration (Minutes)" horizontal v-model="bookingDurationInMin" placeholder="60" maxlength="3" />
                <CInput label="Working Hour From" horizontal v-model="workingHourFrom" placeholder="09:00" maxlength="5" />
                <CInput label="Working Hour To" horizontal v-model="workingHourTo" placeholder="18:00" maxlength="5" />
                <CRow form class="form-group">
                  <CCol tag="label" sm="3" class="col-form-label">Open At</CCol>
                  <CCol sm="9" class="form-inline">
                    <CInputCheckbox label="Mon" v-model="openAtMon" :custom="true" :inline="true" :checked="true" @update:checked="openAtMon=$event" />
                    <CInputCheckbox label="Tue" v-model="openAtTue" :custom="true" :inline="true" :checked="true" @update:checked="openAtTue=$event" />
                    <CInputCheckbox label="Wed" v-model="openAtWed" :custom="true" :inline="true" :checked="true" @update:checked="openAtWed=$event" />
                    <CInputCheckbox label="Thu" v-model="openAtThu" :custom="true" :inline="true" :checked="true" @update:checked="openAtThu=$event" />
                    <CInputCheckbox label="Fri" v-model="openAtFri" :custom="true" :inline="true" :checked="true" @update:checked="openAtFri=$event" />
                    <CInputCheckbox label="Sat" v-model="openAtSat" :custom="true" :inline="true" :checked="true" @update:checked="openAtSat=$event" />
                    <CInputCheckbox label="Sun" v-model="openAtSun" :custom="true" :inline="true" :checked="true" @update:checked="openAtSun=$event" />
                  </CCol>
                </CRow>
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
import storeService from '../../api/store.service.js';
import helper from '../../api/helper.js';

export default {
    data() {
        return {
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
        }
    },
    methods: {
        submitForm() {
          storeService.create(this.$data).then(() => {
            alert("Store created successfully.");
            this.$router.push('/');
          }).catch(err => helper.apiErrorHandling(err, this.$router));
        }
    }
}
</script>