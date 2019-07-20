<template>
    <div class="modal-card" style="width: auto">
        <div class="modal-card-head">
            <header>Products</header>
        </div>
        <section class="modal-card-body">
            <b-table :data="bankProducts">
                <template slot-scope="product">
                    <b-table-column label="#id">
                        {{product.row.id}}
                    </b-table-column>
                    <b-table-column label="Type">
                        {{product.row.type}}
                    </b-table-column>
                    <b-table-column label="Name">
                        {{product.row.name}}
                    </b-table-column>
                    <b-table-column label="Description">
                        {{product.row.description}}
                    </b-table-column>
                    <b-table-column label="Interest Rate">
                        {{product.row.interestRate}}
                    </b-table-column>
                    <b-table-column label="Action">
                        <b-button @click="interestRateForm(product.row)">Modify Intrerest Rate</b-button>
                    </b-table-column>
                </template>
            </b-table>
        </section>
    </div>
</template>

<script>
    import { mapGetters } from 'vuex'
    import InterestRateForm from './InterestRateForm.vue'

    export default {
        name: "BankProducts",
        data() {
            return {
                bankProducts: []
            }
        },
        computed: {
            ...mapGetters(['client'])
        },
        methods: {
            fetchData: async function () {
                const res = await this.client.apis['bank-manager-dashboard'].getBankProductsUsingGET();
                return JSON.parse(res.data)
            },
            interestRateForm: function (props) {
                this.$modal.open({
                    parent: this.$parent,
                    component: InterestRateForm,
                    props: props,
                    hasModalCard: true,
                    onCancel: this.$parent.close()
                })
            }
        },
        created: async function () {
            this.bankProducts = await this.fetchData()
        }

    }
</script>

<style scoped>

</style>