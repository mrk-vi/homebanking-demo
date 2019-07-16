<template>
  <div>
    <nav class="navbar is-fixed-top" role="navigation" aria-label="main navigation">
    <div class="navbar-brand">
      <!-- <a class="navbar-item" href="https://bulma.io">
      <img src="https://bulma.io/images/bulma-logo.png" width="112" height="28">
      </a> -->
      <a class="navbar-item">
          <strong>Homebanking</strong>
      </a>
      <a role="button" class="navbar-burger burger" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample">
        <span aria-hidden="true"></span>
        <span aria-hidden="true"></span>
        <span aria-hidden="true"></span>
      </a>
    </div>
    <div class="navbar-menu">
      <div class="navbar-start">
        <router-link class="navbar-item" to="/">Home</router-link>
        <router-link class="navbar-item" to="/about">About</router-link>
        <!-- <div class="navbar-item has-dropdown is-hoverable">
            <a class="navbar-link">
            More
            </a>

            <div class="navbar-dropdown">
            <a class="navbar-item">
                About
            </a>
            <a class="navbar-item">
                Jobs
            </a>
            <a class="navbar-item">
                Contact
            </a>
            <hr class="navbar-divider">
            <a class="navbar-item">
                Report an issue
            </a>
            </div>
        </div> -->
      </div>

      <div class="navbar-end">
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
    </nav>
    <b-modal :active.sync="isLoginModalActive" has-modal-card>
        <LoginModal/>
    </b-modal>
  </div>
</template>

<script>
import LoginModal from './LoginModal.vue'
import { mapMutations, mapGetters } from 'vuex'
import { UNSET_USER } from '@/store/mutations-types'

export default {
  name: "Navbar",
  components: {
      LoginModal
  },
  data: () => {
    return {
      isLoginModalActive: false
    }
  },
  computed: {
  ...mapGetters(['user', 'auth', 'isManager', 'isAdmin', 'isEmployee'])
  },
  methods: {
    ...mapMutations([UNSET_USER]),
    logout: function (){
      this[UNSET_USER]()
      this.$router.push({name:'home'})
    }
  }
}
</script>

<style>

</style>
