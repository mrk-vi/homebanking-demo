<template>
    <form action="">
        <div class="modal-card">
            <header class="modal-card-head">
                <p class="modal-card-title">Create Bank Branch</p>
            </header>
            <section class="modal-card-body">
                <b-field label="Bank Branch Name">
                    <b-input
                            type="text"
                            v-model="form.bankBranchName"
                            placeholder="Goldman Sachs 1"
                            required
                    >
                    </b-input>
                </b-field>
                <b-field label="Bank Branch Address">
                    <b-input
                            type="text"
                            v-model="form.bankBranchAddress"
                            placeholder="57 Wall st, New York, NY, USA"
                    >
                    </b-input>
                </b-field>
                <b-field label="Opening">
                    <b-clockpicker
                        v-model="form.bankBranchOpening"
                        placeholder="click to select..."
                        icon="clock"
                        :hour-format="'24'">
                    </b-clockpicker>
                </b-field>

                <b-field label="Closing">
                    <b-clockpicker
                            v-model="form.bankBranchClosing"
                            placeholder="click to select..."
                            :hour-format="'24'"
                            icon="clock">
                    </b-clockpicker>
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
    import {mapGetters, mapMutations} from 'vuex'
    import BClockpicker from "buefy/src/components/clockpicker/Clockpicker";
    import {ADD_BRANCH} from "../../../store/mutations-types";
    export default {
        name: "CreateBankBranch",
        components: {BClockpicker},
        data() {
            return {
                form: {
                    bankBranchAddress: '',
                    bankBranchName: '',
                    bankBranchOpening: new Date(0, 0, 0, 9, 0, 0, 0),
                    bankBranchClosing: new Date(0, 0, 0, 20, 0, 0, 0)
                }
            }
        },
        computed: {
            ...mapGetters(['client']),
        },
        methods: {
            submit: async function (evt) {
                evt.preventDefault()
                const parsed = {
                    bankBranchName: this.form.bankBranchName,
                    bankBranchAddress: this.form.bankBranchAddress,
                    bankBranchOpening: this.form.bankBranchOpening.getHours()+':'+this.form.bankBranchOpening.getMinutes(),
                    bankBranchClosing: this.form.bankBranchClosing.getHours()+':'+this.form.bankBranchClosing.getMinutes(),
                }
                const res = await this.client.apis['bank-manager-dashboard'].createBankBranchUsingPOST({form: parsed})
                this[ADD_BRANCH] (JSON.parse(res.data))
                this.$parent.close()
            },
            ...mapMutations([ADD_BRANCH])
        }
    }
</script>

<style scoped>

</style>