<template>
    <div class="container">
        <div class="title">Banks</div>
        <b-table :data="banks">
            <template slot-scope="bank">
                <b-table-column label="#id">
                    {{bank.row.id}}
                </b-table-column>
                <b-table-column label="Name">
                    {{bank.row.name}}
                </b-table-column>
                <b-table-column label="Address">
                    {{bank.row.address}}
                </b-table-column>
                <b-table-column label="Description">
                    {{bank.row.description}}
                </b-table-column>
            </template>
        </b-table>
    </div>
</template>

<script>
    import BTable from "buefy/src/components/table/Table";
    import {mapGetters, mapMutations} from "vuex";
    import {SET_BANKS} from "../store/mutations-types";

    export default {
        name: "AdministratorDashboard",
        components: {BTable},
        computed: {
            ...mapGetters(['client', 'banks'])
        },
        methods:{
            fetchData: async function () {
                const res = await this.client.apis['visitor-controller'].getBanksUsingGET()
                return JSON.parse(res.data)
            },
            ...mapMutations([SET_BANKS])
        },
        created: async function () {
            this[SET_BANKS] (await this.fetchData())
        }
    }
</script>

<style scoped>

</style>