import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/',
            name: 'home',
            component: Home
        },
        {
            path: '/employee',
            name: 'employee',
            component: () => import('../views/EmployeeDashboard.vue')
        },
        {
            path: '/manager',
            name: 'manager',
            component: () => import('../views/ManagerDashboard.vue')
        },
        {
            path: '/customer',
            name: 'customer',
            component: () => import('../views/CustomerDashboard.vue')
        },
        {
            path: '/admin',
            name: 'admin',
            component: () => import('../views/AdministratorDashboard.vue')
        }
    ]
})
