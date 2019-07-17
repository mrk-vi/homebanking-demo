<template>
    <div class="modal-card" style="width: auto">
        <div class="modal-card-head">
            <header>Unauthorized Customers</header>
        </div>
        <section class="modal-card-body">
            <b-table :data="unauthorizedList">
                <template slot-scope="customer">
                    <b-table-column label="#id">
                        {{customer.row.id}}
                    </b-table-column>
                    <b-table-column label="Username">
                        {{customer.row.user.username}}
                    </b-table-column>
                    <b-table-column label="Bankbranch">
                        {{customer.row.bankBranch.name}}
                    </b-table-column>
                    <b-table-column label="Action">
                        <b-button @click="authorize(customer.row.id)">Authorize</b-button>
                    </b-table-column>
                </template>
            </b-table>
        </section>
    </div>
</template>

<script>
    import { mapGetters } from 'vuex'

    export default {
        name: "UnauthorizedList",
        data(){
            return {
                unauthorizedList: []
            }
        },
        computed: {
            ...mapGetters(['client'])
        },
        methods: {
            fetchData: async function () {
                const res = await this.client.apis['admin-dashboard'].getCustomersUsingGET()
                return JSON.parse(res.data)
            },
            authorize: async function (id) {
                await this.client.apis['admin-dashboard'].authorizeCustomerUsingPUT({id: id})
                this.unauthorizedList = this.unauthorizedList.filter( (item) => item.id !== id )
            }
        },
        created: async function () {
            this.unauthorizedList = await this.fetchData()
        }
    }
</script>

<style scoped>

</style>