<template>
  <CDropdown
    inNav
    class="c-header-nav-items"
    placement="bottom-end"
    add-menu-classes="pt-0"
  >
    <template #toggler>
      <CHeaderNavLink>
        <div class="c-avatar">
          <img
            src="img/avatars/user.png"
            class="c-avatar-img "
          />
        </div>
      </CHeaderNavLink>
    </template>
    <CDropdownHeader
      tag="div"
      class="text-center"
      color="light"
    >
      <strong>Settings</strong>
    </CDropdownHeader>
    <CDropdownItem>
      <CIcon name="cil-settings" /> Settings
    </CDropdownItem>
    <CDropdownItem>
      <span @click="logout()">
        <CIcon name="cil-lock-locked" /> Logout
      </span>
    </CDropdownItem>
  </CDropdown>
</template>

<script>
import adminUserService from '../api/admin-user.service.js';

export default {
  name: 'TheHeaderDropdownAccnt',
  data () {
    return { 
    }
  },
  methods: {
    logout() {
      adminUserService.logout();
      this.$router.push("/pages/login");
    }
  }
}
</script>

<style scoped>
  .c-icon {
    margin-right: 0.3rem;
  }
</style>