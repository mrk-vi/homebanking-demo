<template>
        <div>
            <b-select placeholder="Select a bank account"
                      icon="wallet"
                      size="is-default"
                      v-on:input="$emit('input', $event.id)
                                  balance = $event.balance">
                <option v-for="account in accounts"
                        :value="account"
                        :key="account.id">
                    {{account.id}} {{account.iban}} ({{account.bankProduct.name}})
                </option>
            </b-select>
            <b-field label="Current Balance" v-show="balance">
                <strong>{{balance}}€</strong>
            </b-field>
        </div>
</template>

<script>
    import BSelect from "buefy/src/components/select/Select";
    import {mapGetters} from "vuex";
    import BField from "buefy/src/components/field/Field";
    export default {
        name: "BankAccount",
        components: {BField, BSelect},
        data() {
            return {
                accounts: [],
                balance: null
            }
        },
        computed: {
            ...mapGetters(['client'])
        },
        methods: {
            fetchData: async function () {
                const res = await this.client.apis['customer-dashboard'].getBankAccountsUsingGET()
                return JSON.parse(res.data)
            }
        },
        created: async function () {
            this.accounts = await this.fetchData()
        }

    }
</script>

<style scoped>

</style>