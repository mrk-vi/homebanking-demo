<template>
    <form>
        <div class="modal-card" style="width: auto">
            <header class="modal-card-head">
                <p class="modal-card-title">Authentication</p>
            </header>
            <section class="modal-card-body">
                <b-field label="Username">
                    <b-input
                        type="text"
                        v-model="username"
                        placeholder="Your username"
                        required>
                    </b-input>
                </b-field>

                <b-field label="Password">
                    <b-input
                        type="password"
                        v-model="password"
                        password-reveal
                        placeholder="Your password"
                        required>
                    </b-input>
                </b-field>

                <!-- <b-checkbox>Remember me</b-checkbox> -->
            </section>
            <footer class="modal-card-foot">
                <button class="button" type="button" @click="$parent.close()">Close</button>
                <button class="button is-primary" @click="fetchData">Login</button>
            </footer>
        </div>
    </form>
</template>

<script>
import { basicAuthClient } from '@/api'
import { mapMutations, mapGetters } from 'vuex'
import { SET_USER } from '@/store/mutations-types'
export default {
    name: 'LoginModal',
    data: () => {
        return {
            username: '',
            password: ''
        }
    },
    computed: {
        ...mapGetters(['user', 'auth', 'isManager', 'isAdmin', 'isEmployee'])
    },
    methods: {
        fetchData: async function (evt) {
            evt.preventDefault()
            try {
                const client = await basicAuthClient(this.username, this.password)
                const res = await client.apis['authentication-controller'].getUserInfoUsingGET()
                this[SET_USER](JSON.parse(res.data))
                this.$parent.close()
            } catch (err) {
                console.log(err)
            }
        },
        ...mapMutations([SET_USER])
    }
}
</script>

<style>

</style>
