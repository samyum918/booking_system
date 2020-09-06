<template>
    <div class="vue-tempalte">
        <form>
            <h3>Sign In</h3>

            <div class="form-group">
                <label>Username</label>
                <input type="text" v-model="username" class="form-control form-control-lg" required />
            </div>

            <div class="form-group">
                <label>Password</label>
                <input type="password" v-model="password" class="form-control form-control-lg" required />
            </div>

            <button type="button" @click="login()" class="btn btn-dark btn-lg btn-block btn-margin">Sign In</button>

            <p class="forgot-password text-right mt-2 mb-4">
                <router-link to="/forgot-password">Forgot password ?</router-link>
            </p>

        </form>
    </div>
</template>

<script>
    import userService from '../api/user.service.js';

    export default {
        data() {
            return {
                username: null,
                password: null
            };
        },
        methods: {
            login() {
                const username = this.$data.username;
                const password = this.$data.password;
                userService.login(username, password).then(() => {
                    this.$router.push('/booking');
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