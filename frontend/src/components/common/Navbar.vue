<template>
    <nav class="navbar is-fixed-top">
<!--        role="navigation" aria-label="main navigation">-->
        <div class="container">
            <div class="navbar-brand">
            <!-- <a class="navbar-item" href="https://bulma.io">
            <img src="https://bulma.io/images/bulma-logo.png" width="112" height="28">
            </a> -->
            <a class="navbar-item">
                <strong>Homebanking</strong>
            </a>
            <a role="button" class="navbar-burger burger" aria-label="menu" aria-expanded="false"
               data-target="navbarBasicExample">
                <span aria-hidden="true"></span>
                <span aria-hidden="true"></span>
                <span aria-hidden="true"></span>
            </a>
            </div>

            <div id="navbarBasicExample" class="navbar-menu">
                <div class="navbar-start">
                    <router-link v-show="!auth" class="navbar-item" to="/">Home</router-link>
                    <router-link v-show="isEmployee" class="navbar-item" to="/employee">Dashboard</router-link>
                    <router-link v-show="isManager" class="navbar-item" to="/manager">Dashboard</router-link>
                    <router-link v-show="isCustomer" class="navbar-item" to="/customer">Dashboard</router-link>
                    <router-link v-show="isAdmin" class="navbar-item" to="/admin">Dashboard</router-link>

                </div>

                <div class="navbar-end">
                    <CustomerNavbar v-show="isCustomer"/>
                    <AdminNavbar v-show="isAdmin"/>
                    <ManagerNavbar v-show="isManager"/>
    <!--                    <EmployeeNavbar v-show="isEmployee"/>-->
                    <div class="navbar-item">
                        <div class="buttons">
                            <button v-show="!auth" class="button is-primary" @click="isLoginModalActive = true">
                                <strong>Log in</strong>
                            </button>
                            <button v-show="auth" class="button is-primary" @click="logout">
                                <strong>Log out</strong>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <b-modal :active.sync="isLoginModalActive" has-modal-card>
                <LoginModal/>
            </b-modal>
        </div>
    </nav>
</template>

<script>
    import LoginModal from './LoginModal.vue'
    import AdminNavbar from './AdminNavbar.vue'
    import ManagerNavbar from './ManagerNavbar.vue'
    // import EmployeeNavbar from './EmployeeNavbar.vue'
    import CustomerNavbar from './CustomerNavbar.vue'

    import {mapMutations, mapGetters} from 'vuex'
    import {UNSET_USER, UNSET_CLIENT} from '../../store/mutations-types'

    export default {
        name: "Navbar",
        components: {
            LoginModal,
            AdminNavbar,
            ManagerNavbar,
            //EmployeeNavbar,
            CustomerNavbar
        },
        data() {
            return {
                isLoginModalActive: false
            }
        },
        computed: {
            ...mapGetters(['user', 'auth', 'isManager', 'isAdmin', 'isEmployee', 'isCustomer'])
        },
        methods: {
            ...mapMutations([UNSET_USER, UNSET_CLIENT]),
            logout: function () {
                this[UNSET_USER]()
                this[UNSET_CLIENT]()
                this.$router.push({name: 'home'})
            }
        }
    }
</script>

<style>

</style>
