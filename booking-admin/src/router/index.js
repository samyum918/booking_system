import Vue from 'vue'
import Router from 'vue-router'
import store from '../store'

// Containers
const TheContainer = () => import('@/containers/TheContainer')

// Views
const StoreCreation = () => import('@/views/store-mgmt/StoreCreation')
const StoreOverview = () => import('@/views/store-mgmt/StoreOverview')

const StoreTimeslotOverview = () => import('@/views/store-mgmt/StoreTimeslotOverview')

const BlockDate = () => import('@/views/block-booking/BlockDate')
const BlockTimeslot = () => import('@/views/block-booking/BlockTimeslot')

// Views - Components
const Forms = () => import('@/views/base/Forms')
const Tables = () => import('@/views/base/Tables')
const Breadcrumbs = () => import('@/views/base/Breadcrumbs')
const Paginations = () => import('@/views/base/Paginations')

// Views - Pages
const Page404 = () => import('@/views/pages/Page404')
const Page500 = () => import('@/views/pages/Page500')
const Login = () => import('@/views/pages/Login')
const Register = () => import('@/views/pages/Register')

// Users
const Users = () => import('@/views/users/Users')
const User = () => import('@/views/users/User')

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
              path: 'creation',
              name: 'Store Creation',
              component: StoreCreation
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
          path: 'users',
          meta: {
            label: 'Users'
          },
          component: {
            render(c) {
              return c('router-view')
            }
          },
          children: [
            {
              path: '',
              name: 'Users',
              component: Users
            },
            {
              path: ':id',
              meta: {
                label: 'User Details'
              },
              name: 'User',
              component: User
            }
          ]
        },
        {
          path: 'base',
          redirect: '/base/forms',
          name: 'Base',
          component: {
            render (c) { return c('router-view') }
          },
          children: [
            {
              path: 'forms',
              name: 'Forms',
              component: Forms
            },
            {
              path: 'tables',
              name: 'Tables',
              component: Tables
            },
            {
              path: 'breadcrumbs',
              name: 'Breadcrumbs',
              component: Breadcrumbs
            },
            {
              path: 'paginations',
              name: 'Paginations',
              component: Paginations
            }
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