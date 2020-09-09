import Vue from 'vue'
import Vuex from 'vuex'
import auth from './api/auth'
Vue.use(Vuex)

const state = {
  sidebarShow: 'responsive',
  sidebarMinimize: false,
  isLogged: auth.isLoggedIn(),
  adminUser: auth.getAdminUserObj()
}

const mutations = {
  toggleSidebarDesktop (state) {
    const sidebarOpened = [true, 'responsive'].includes(state.sidebarShow)
    state.sidebarShow = sidebarOpened ? false : 'responsive'
  },
  toggleSidebarMobile (state) {
    const sidebarClosed = [false, 'responsive'].includes(state.sidebarShow)
    state.sidebarShow = sidebarClosed ? true : 'responsive'
  },
  login(state, adminUser) {
    state.isLogged = true
    state.adminUser = adminUser
  },
  logout(state) {
    state.isLogged = false
    state.adminUser = null
  },
  set (state, [variable, value]) {
    state[variable] = value
  }
}

export default new Vuex.Store({
  state,
  mutations
})