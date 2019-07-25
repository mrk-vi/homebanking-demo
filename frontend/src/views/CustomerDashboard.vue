<template>
    <div class="container">
        <div class="title">Accounts</div>
        <b-table :data="bankAccounts">
            <template slot-scope="account">
                <b-table-column label="#id">
                    {{account.row.id}}
                </b-table-column>
                <b-table-column label="Bank Product">
                    {{account.row.bankProduct.name}}
                </b-table-column>
                <b-table-column label="IBAN">
                    {{account.row.iban}}
                </b-table-column>
                <b-table-column label="Balance">
                    {{account.row.balance}} €
                </b-table-column>
            </template>
        </b-table>
    </div>
</template>

<script>
    import BTable from "buefy/src/components/table/Table";
    import {mapGetters} from "vuex";

    export default {
        name: "CustomerDashboard",
        components: {BTable},
        data() {
            return {
                bankAccounts: []
            }
        },
        computed: {
          ...mapGetters(['client'])
        },
        methods:{
            fetchData: async function () {
                const res = await this.client.apis['customer-dashboard'].getBankAccountsUsingGET()
                return JSON.parse(res.data)
            }
        },
        created: async function () {
            this.bankAccounts = await this.fetchData()
        }
    }
</script>

<style scoped>

</style>