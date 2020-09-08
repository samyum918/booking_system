<template>
    <nav class="navbar shadow bg-white rounded justify-content-between flex-nowrap flex-row absolute-top">
      <div class="container">
        <a class="navbar-brand float-left" href="/">
           Booking site
        </a>
        <ul class="nav navbar-nav flex-row float-right" v-if="isLogged === false">
          <li class="nav-item">
            <router-link class="nav-link pr-3" to="/login">Sign in</router-link>
          </li>
          <li class="nav-item">
            <router-link class="btn btn-outline-primary" to="/signup">Sign up</router-link>
          </li>
        </ul>
        <ul class="nav flex-row float-right" v-if="isLogged === true">
          <li class="nav-item">
            <div class="navbar-username">{{username}}</div>
          </li>
          <li class="nav-item">
            <div class="btn-group">
                <button type="button" class="btn btn-outline-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                    <path d="M13.468 12.37C12.758 11.226 11.195 10 8 10s-4.757 1.225-5.468 2.37A6.987 6.987 0 0 0 8 15a6.987 6.987 0 0 0 5.468-2.63z"/>
                    <path fill-rule="evenodd" d="M8 9a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
                    <path fill-rule="evenodd" d="M8 1a7 7 0 1 0 0 14A7 7 0 0 0 8 1zM0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8z"/>
                    </svg>
                </button>
                <div class="dropdown-menu dropdown-menu-right">
                    <a class="dropdown-item" href="/booking">Book a service</a>
                    <a class="dropdown-item" href="#">Booking History</a>
                    <div class="dropdown-divider"></div>
                    <div class="dropdown-item cursor-pointer" @click="logout()">Logout</div>
                </div>
            </div>
          </li>
        </ul>
      </div>
    </nav>
</template>
<script>
import auth from '../api/auth.js';
import userService from '../api/user.service.js';
import EventBus from '../event-bus';

export default {
    template: '<Navigation />',
    name: 'navigation',
    data() {
        return {
            isLogged: auth.isLoggedIn(),
            username: null
        }
    },
    methods: {
        logout() {
            userService.logout();
            this.$router.push('/');
        },
        unsetNavBar() {
            this.isLogged = false;
            this.username = null;
        }
    },
    created() {
        if(auth.getUsername()) {
            this.username = auth.getUsername();
        }
        EventBus.$on('logged', () => {
            this.isLogged = auth.isLoggedIn();
            this.username = auth.getUsername();
        });
        EventBus.$on('logout', () => {
            this.unsetNavBar();
        });
    }
}
</script>