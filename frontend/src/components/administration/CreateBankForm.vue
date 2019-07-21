<template>
    <form action="">
        <div class="modal-card" style="width: 680px">
            <header class="modal-card-head">
                <p class="modal-card-title">Create Bank</p>
            </header>
            <section class="modal-card-body">
                <b-field label="Bank Name">
                    <b-input
                            type="text"
                            v-model="form.bankName"
                            placeholder="Goldman Sachs"
                            required
                    >
                    </b-input>
                </b-field>
                <b-field label="Bank Description">
                    <b-input
                            type="textarea"
                            v-model="form.bankDescription"
                            placeholder="The Goldman Sachs Group, Inc. is a leading global investment banking, securities and investment management firm that provides a wide range of financial ..."
                    >
                    </b-input>
                </b-field>
                <b-field label="Bank Address">
                    <b-input
                            type="text"
                            v-model="form.bankAddress"
                            placeholder="57 Wall st, New York, NY, USA"
                    >
                    </b-input>
                </b-field>
                <b-field label="Email">
                    <b-input
                            type="email"
                            v-model="form.mail"
                            placeholder="Manager email"
                            required>
                    </b-input>
                </b-field>

                <b-field label="Username">
                    <b-input
                            type="text"
                            v-model="form.username"
                            placeholder="Manager username"
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
    import {mapGetters} from 'vuex'
    import BField from "buefy/src/components/field/Field";
    import BInput from "buefy/src/components/input/Input";

    export default {
        name: "CreateBankForm",
        data: () => {
            return {
                form: {
                    bankName: '',
                    bankDescription: '',
                    bankAddress: '',
                    mail: '',
                    username: ''
                }
            }
        },
        components: {BInput, BField},
        computed: {
            ...mapGetters(['client'])
        },
        methods: {
            submit: function (evt) {
                evt.preventDefault()
                this.client.apis['admin-dashboard'].createBankUsingPOST({form: this.form})
                this.$parent.close()
            }
        }
    }
</script>

<style scoped>

</style>