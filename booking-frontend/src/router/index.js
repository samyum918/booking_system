import Vue from 'vue'
import VueRouter from 'vue-router'
import auth from '../api/auth';

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'home',
    component: () => import('../components/Home.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../components/Login.vue')
  },
  {
    path: '/signup',
    name: 'signup',
    component: () => import('../components/Signup.vue')
  },
  {
    path: '/forgot-password',
    name: 'forgot-password',
    component: () => import('../components/ForgotPassword.vue')
  },
  {
    path: '/booking',
    name: 'booking',
    component: () => import('../components/Booking.vue')
  },
  {
    path: '/booking-history',
    name: 'booking-history',
    component: () => import('../components/BookingHistory.vue')
  },
  {
    path: '/thankyou',
    name: 'thankyou',
    component: () => import('../components/Thankyou.vue')
  },
  { path: '*', redirect: '/' }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  // redirect to login page if not logged in and trying to access a restricted page
  const publicPages = ['/', '/signup', '/login'];
  const authRequired = !publicPages.includes(to.path);

  if (authRequired && !auth.isLoggedIn()) {
    return next('/login');
  }

  next();
});

export default router
