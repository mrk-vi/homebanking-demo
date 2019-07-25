<template>
    <form action="">
        <div class="modal-card" style="width: auto">
            <header class="modal-card-head">
                <p class="modal-card-title">Transfer Operation</p>
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
                <b-field label="Recipient Iban">
                    <b-input required v-model="form.recipientIban" type="text" placeholder="IT0000...33441">
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
    import BField from "buefy/src/components/field/Field";
    import BInput from "buefy/src/components/input/Input";
    import {mapGetters} from "vuex";
    export default {
        name: "Transfer",
        components: {BInput, BField, BankAccount},
        data(){
            return {
                form: {
                    amount: null,
                    recipientIban: null
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
                await this.client.apis['customer-dashboard'].transferUsingPOST({id: this.bankAccountId, form: this.form})
                this.$parent.close()
            },
        }
    }
</script>

<style scoped>

</style>