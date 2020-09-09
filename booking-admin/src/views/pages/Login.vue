<template>
  <div class="c-app flex-row align-items-center">
    <CContainer>
      <CRow class="justify-content-center">
        <CCol md="8">
          <CCardGroup>
            <CCard class="p-4">
              <CCardBody>
                <CForm>
                  <h1>Login</h1>
                  <p class="text-muted">Sign In to admin account</p>
                  <CInput 
                    v-model="username"
                    placeholder="Username"
                    autocomplete="username email"
                  >
                    <template #prepend-content><CIcon name="cil-user"/></template>
                  </CInput>
                  <CInput 
                    v-model="password"
                    placeholder="Password"
                    type="password"
                    autocomplete="curent-password"
                  >
                    <template #prepend-content><CIcon name="cil-lock-locked"/></template>
                  </CInput>
                  <CRow>
                    <CCol col="6" class="text-left">
                      <CButton color="primary" class="px-4" @click="login()">Login</CButton>
                    </CCol>
                    <CCol col="6" class="text-right">
                      <CButton color="link" class="px-0">Forgot password?</CButton>
                    </CCol>
                  </CRow>
                </CForm>
              </CCardBody>
            </CCard>
            <CCard color="primary" text-color="white" class="text-center py-5 d-md-down-none" body-wrapper>
              <CCardBody>
                <h2>Sign up</h2>
                <p></p>
                <a href="/#/pages/register" class="btn btn-outline-light btn-lg" role="button">Register Now!</a>
              </CCardBody>
            </CCard>
          </CCardGroup>
        </CCol>
      </CRow>
    </CContainer>
  </div>
</template>

<script>
import adminUserService from '../../api/admin-user.service';

export default {
  name: 'Login',
  data() {
    return {
      username: null,
      password: null
    }
  },
  methods: {
    login() {
      const username = this.$data.username;
      const password = this.$data.password;
      adminUserService.login(username, password).then(() => {
          this.$router.push('/');
      }).catch(err => {
          if(err.status === 403) {
              alert("Login failed. Error: " + err.data.error);
          }
          else {
              alert("An unexpected error occured.");
              console.log(err);
          }
      });
    }
  }
}
</script>
