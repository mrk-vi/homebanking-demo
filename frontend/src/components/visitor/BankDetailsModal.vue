<template>
    <div class="modal-card" style="width: auto">
        <div class="modal-card-head">
            <header>{{name}} Products and Serices</header>
        </div>
        <section class="modal-card-body">
            <section>
                <div class="title">Products</div>
                <b-table :data="bankProducts" :columns="columns"></b-table>
            </section>
            <section>
                <div class="title">Services</div>
                <ul>
                    <li v-for="service in bankServices" v-bind:key="service.id">
                        {{service.type}}
                    </li>
                </ul>
            </section>
        </section>
    </div>
</template>

<script>
    import {publicClient} from '../../service'

    export default {
        name: 'BankDetailsModal',
        props: ['id', 'name'],
        data: () => {
            return {
                bankProducts: [],
                bankServices: [],
                columns: [
                    {
                        field: "type",
                        label: "Type"
                    },
                    {
                        field: "name",
                        label: "Name"
                    },
                    {
                        field: "description",
                        label: "Description"
                    },
                    {
                        field: "interestRate",
                        label: "Interest Rate"
                    }
                ]
            }
        },
        methods: {
            fetchBankProducts: async function () {
                const client = await publicClient()
                const res = await client.apis['visitor-controller'].getBankProductsUsingGET_1({id: this.id})
                return JSON.parse(res.data)
            },
            fetchBankServices: async function () {
                const client = await publicClient()
                const res = await client.apis['visitor-controller'].getBankServiceUsingGET({id: this.id})
                return JSON.parse(res.data)
            }
        },
        created: async function () {
            this.bankProducts = await this.fetchBankProducts()
            this.bankServices = await this.fetchBankServices()
        }
    }
</script>

<style>

</style>
