<template>
    <form action="">
        <div class="modal-card" style="width: 680px">
            <header class="modal-card-head">
                <p class="modal-card-title">Registration for branch {{name}} [id: {{id}}]</p>
            </header>
            <section class="modal-card-body">
                <b-field label="Email">
                    <b-input
                            type="email"
                            v-model="form.mail"
                            placeholder="Your email"
                            required>
                    </b-input>
                </b-field>

                <b-field label="Username">
                    <b-input
                            type="text"
                            v-model="form.username"
                            placeholder="Your username"
                            required>
                    </b-input>
                </b-field>

                <b-field label="Password">
                    <b-input
                            type="text"
                            v-model="form.password"
                            placeholder="Your password"
                            required>
                    </b-input>
                </b-field>
            </section>
            <footer class="modal-card-foot">
                <button class="button" type="button" @click="$parent.close()">Close</button>
                <button class="button is-primary" type="submit" @click="submit">Submit</button>
            </footer>
        </div>
    </form>
</template>

<script>
    import { publicClient } from '../../service'

    export default {
        name: "RegistrationFormModal",
        props: ['id', 'name'],
        data(){
            return {
                form: {
                    bankBranchId: this.id,
                    mail: '',
                    username: '',
                    password: ''
                }
            }
        },
        methods: {
            submit: async function (evt){
                evt.preventDefault()
                const client = await publicClient()
                await client.apis['visitor-controller'].registrationRequestUsingPOST({form: this.form})
                this.$parent.close()
            }
        }
    }
</script>

<style scoped>

</style>