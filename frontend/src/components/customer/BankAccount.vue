<template>
        <b-select placeholder="Select a bank account"
                  icon="wallet"
                  size="is-default"
                  v-on:input="$emit('input', $event)">
            <option v-for="account in accounts"
                    :value="account.id"
                    :key="account.id">
                {{account.id}} {{account.iban}} ({{account.bankProduct.name}})
            </option>
        </b-select>
</template>

<script>
    import BSelect from "buefy/src/components/select/Select";
    import {mapGetters} from "vuex";
    export default {
        name: "BankAccount",
        components: {BSelect},
        data() {
            return {
                accounts: []
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