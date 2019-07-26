<template>
    <form action="">
        <div class="modal-card" style="width: auto">
            <header class="modal-card-head">
                <p class="modal-card-title">Car Tax</p>
            </header>
            <section class="modal-card-body">
                <b-field label="Bank Account">
                    <bank-account v-model="bankAccountId"></bank-account>
                </b-field>
                <b-field label="Amount">
                    <b-numberinput v-model="form.amount"
                                   controls-position="compact"
                                   step="1" min="1"
                                   icon="currency-eur">
                    </b-numberinput>
                </b-field>
                <b-field label="License plate">
                    <b-input required v-model="form.licensePlate" type="text" placeholder="EJ329EE">
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
    import BankAccount from "./BankAccount";
    import {mapGetters, mapMutations} from "vuex";
    import BField from "buefy/src/components/field/Field";
    import {SET_BANK_ACCOUNTS} from "../../../store/mutations-types";
    export default {
        name: "CarTax",
        components: {BField, BankAccount},
        data(){
            return {
                form: {
                    amount: null,
                    licensePlate: null
                },
                bankAccountId: null
            }
        },
        computed: {
            ...mapGetters(['client'])
        },
        methods: {
            submit: async function (evt) {
                evt.preventDefault()
                await this.client.apis['customer-dashboard'].carTaxUsingPOST({id: this.bankAccountId, form: this.form})
                this[SET_BANK_ACCOUNTS](await this.fetchBankAccounts())
                this.$parent.close()
            },
            fetchBankAccounts: async function () {
                const res = await this.client.apis['customer-dashboard'].getBankAccountsUsingGET()
                return JSON.parse(res.data)
            },
            ...mapMutations([SET_BANK_ACCOUNTS])
        }
    }
</script>

<style scoped>

</style>