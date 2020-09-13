import Vue from 'vue'
import Router from 'vue-router'
import store from '../store'

// Containers
const TheContainer = () => import('@/containers/TheContainer')

// Views
const StoreOverview = () => import('@/views/store-mgmt/StoreOverview')
const StoreAddEdit = () => import('@/views/store-mgmt/StoreAddEdit')

const StoreTimeslotOverview = () => import('@/views/store-mgmt/StoreTimeslotOverview')
const StoreTimeslotAddEdit = () => import('@/views/store-mgmt/StoreTimeslotAddEdit')

const BlockDate = () => import('@/views/block-booking/BlockDate')
const BlockTimeslot = () => import('@/views/block-booking/BlockTimeslot')

const BookingOverview = () => import('@/views/records/BookingOverview')
const CancelBookingOverview = () => import('@/views/records/CancelBookingOverview')
const UserOverview = () => import('@/views/records/UserOverview')
const DateBlockingOverview = () => import('@/views/records/DateBlockingOverview')
const TimeslotBlockingOverview = () => import('@/views/records/TimeslotBlockingOverview')

// Views - Pages
const Page404 = () => import('@/views/pages/Page404')
const Page500 = () => import('@/views/pages/Page500')
const Login = () => import('@/views/pages/Login')
const Register = () => import('@/views/pages/Register')

Vue.use(Router)

const router = new Router({
  mode: 'hash', // https://router.vuejs.org/api/#mode
  linkActiveClass: 'active',
  scrollBehavior: () => ({ y: 0 }),
  routes: configRoutes()
});

router.beforeEach((to, from, next) => {
  const publicPages = ['/pages/login'];
  const authRequired = !publicPages.includes(to.path);

  if (authRequired && !store.state.isLogged) {
    return next('/pages/login');
  }

  next();
});

function configRoutes () {
  return [
    {
      path: '/',
      redirect: '/store/overview',
      name: 'Home',
      component: TheContainer,
      children: [
        {
          path: 'store',
          redirect: '/store/overview',
          name: 'Store',
          component: {
            render (c) { return c('router-view') }
          },
          children: [
            {
              path: 'overview',
              name: 'Store Overview',
              component: StoreOverview
            },
            {
              path: 'add-edit',
              name: 'Store Add/Edit',
              component: StoreAddEdit
            }
          ]
        },
        {
          path: 'store-timeslot',
          redirect: '/store-timeslot/overview',
          name: 'Store Timeslot',
          component: {
            render (c) { return c('router-view') }
          },
          children: [
            {
              path: 'overview',
              name: 'Store Timeslot Overview',
              component: StoreTimeslotOverview
            },
            {
              path: 'add-edit',
              name: 'Store Timeslot Add/Edit',
              component: StoreTimeslotAddEdit
            }
          ]
        },
        {
          path: 'block-booking',
          redirect: '/block-booking/timeslot',
          name: 'Block Booking',
          component: {
            render (c) { return c('router-view') }
          },
          children: [
            {
              path: 'date',
              name: 'Block Date',
              component: BlockDate
            },
            {
              path: 'timeslot',
              name: 'Block Timeslot',
              component: BlockTimeslot
            }
          ]
        },
        {
          path: 'records',
          redirect: '/records/booking',
          name: 'Records',
          component: {
            render (c) { return c('router-view') }
          },
          children: [
            {
              path: 'booking',
              name: 'Booking Records',
              component: BookingOverview
            },
            {
              path: 'cancel-booking',
              name: 'Cancel Booking Records',
              component: CancelBookingOverview
            },
            {
              path: 'user',
              name: 'User Records',
              component: UserOverview
            },
            {
              path: 'date-blocking',
              name: 'Date Blocking Records',
              component: DateBlockingOverview
            },
            {
              path: 'timeslot-blocking',
              name: 'Timeslot Blocking Records',
              component: TimeslotBlockingOverview
            },
          ]
        }
      ]
    },
    {
      path: '/pages',
      redirect: '/pages/404',
      name: 'Pages',
      component: {
        render (c) { return c('router-view') }
      },
      children: [
        {
          path: '404',
          name: 'Page404',
          component: Page404
        },
        {
          path: '500',
          name: 'Page500',
          component: Page500
        },
        {
          path: 'login',
          name: 'Login',
          component: Login
        },
        {
          path: 'register',
          name: 'Register',
          component: Register
        }
      ]
    }
  ]
}

export default router