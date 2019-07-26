<template>
    <form action="">
        <div class="modal-card" style="width: auto">
            <header class="modal-card-head">
                <p class="modal-card-title">Refill</p>
            </header>
            <section class="modal-card-body">
                <b-field label="Bank Account">
                    <bank-account v-model="bankAccountId"></bank-account>
                </b-field>
                <b-field label="Amount">
                    <b-select v-model="form.amount"
                              icon="currency-eur">
                        <option>10</option>
                        <option>15</option>
                        <option>20</option>
                        <option>50</option>
                        <option>100</option>
                    </b-select>
                </b-field>
                <b-field label="Provider">
                    <b-select>
                        <option>Tim</option>
                        <option>Vodafone</option>
                        <option>Tre</option>
                        <option>Wind</option>
                        <option>Iliad</option>
                    </b-select>
                </b-field>
                <b-field label="Mobile number">
                    <b-input required v-model="form.phoneNumber" type="text" placeholder="3392214330">
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
    import BSelect from "buefy/src/components/select/Select";
    import {SET_BANK_ACCOUNTS} from "../../../store/mutations-types";
    export default {
        name: "Refill",
        components: {BSelect, BField, BankAccount},
        data(){
            return {
                form: {
                    amount: null,
                    phoneNumber: null
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
                await this.client.apis['customer-dashboard'].refillUsingPOST({id: this.bankAccountId, form: this.form})
                this[SET_BANK_ACCOUNTS] (await this.fetchBankAccounts())
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