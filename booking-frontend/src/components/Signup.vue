<template>
    <div class="vue-tempalte">
        <form>
            <h3>Sign Up</h3>

            <div class="form-group">
                <label>Username</label>
                <input type="text" v-model="username" class="form-control form-control-lg"/>
            </div>

            <div class="form-group">
                <label>Password</label>
                <input type="password" v-model="password" class="form-control form-control-lg" />
            </div>

            <div class="form-group">
                <label>First Name</label>
                <input type="text" v-model="firstName" class="form-control form-control-lg"/>
            </div>

            <div class="form-group">
                <label>Last Name</label>
                <input type="text" v-model="lastName" class="form-control form-control-lg"/>
            </div>

            <div class="form-group">
                <label>Gender</label>
                <div class="radio">
                    <span class="radio-option">
                        <input type="radio" name="gender" v-model="gender" value="M" class="cursor-pointer" />
                        <span class="radio-label">Male</span>
                    </span>
                    <span class="radio-option">
                        <input type="radio" name="gender" v-model="gender" value="F" class="cursor-pointer" />
                        <span class="radio-label">Female</span>
                    </span>
                </div>
            </div>

            <div class="form-group">
                <label>Email address</label>
                <input type="email" v-model="email" class="form-control form-control-lg" />
            </div>

            <div class="form-group">
                <label>Phone</label>
                <input type="tel" v-model="phone" class="form-control form-control-lg" />
            </div>

            <button type="button" @click="register()" class="btn btn-dark btn-lg btn-block btn-margin">Sign Up</button>

            <p class="forgot-password text-right">
                Already registered 
                <router-link :to="{name: 'login'}">sign in?</router-link>
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
                password: null,
                firstName: null,
                lastName: null,
                gender: null,
                email: null,
                phone: null
            }
        },
        methods: {
            register() {
                userService.register(this.$data).then(() => {
                    alert("Register success. Please login.");
                    this.$router.push('/login');
                }).catch(err => {
                    if(err.status === 400 || err.status === 403) {
                        alert("Register failed. Error: " + err.data.error);
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