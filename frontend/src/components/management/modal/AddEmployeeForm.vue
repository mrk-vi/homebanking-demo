<template>
    <form action="">
        <div class="modal-card" style="width: 680px">
            <header class="modal-card-head">
                <p class="modal-card-title">Add a new employee</p>
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
            </section>
            <footer class="modal-card-foot">
                <button class="button" type="button" @click="$parent.close()">Close</button>
                <button class="button is-primary" type="submit" @click="submit">Submit</button>
            </footer>
        </div>
    </form>
</template>

<script>
    import { mapGetters } from 'vuex'
    export default {
        name: "AddEmployeeForm",
        props: ['id'],
        data(){
            return {
                form: {
                    mail:'',
                    username:''
                }
            }
        },
        computed: {
            ...mapGetters(['client'])
        },
        methods: {
            submit: async function (evt) {
                evt.preventDefault()
                await this.client.apis['bank-manager-dashboard'].assignEmployeeUsingPOST({id: this.id, form: this.form})
                this.$parent.close()
            }
        }


    }
</script>

<style scoped>

</style>