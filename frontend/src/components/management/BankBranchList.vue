<template>
    <div class="container">
        <div class="title">
            Branches
        </div>
        <section class="modal-card-body">
            <b-table :data="branches">
                <template slot-scope="branch">
                    <b-table-column label="#id">
                        {{branch.row.id}}
                    </b-table-column>
                    <b-table-column label="Bank Branch Name">
                        {{branch.row.name}}
                    </b-table-column>
                    <b-table-column label="Bank Branch Address">
                        {{branch.row.address}}
                    </b-table-column>
                    <b-table-column label="Action">
                        <b-button @click="assignEmployeeForm(branch.row)">Add Employee</b-button>
                    </b-table-column>
                </template>
            </b-table>
        </section>
    </div>
</template>

<script>
    import {mapGetters, mapMutations} from 'vuex'
    import AddEmployeeForm from './modal/AddEmployeeForm.vue'
    import {SET_BRANCHES} from "../../store/mutations-types";
    export default {
        name: "Branches",
        data(){
            return {
                //branches: []
            }
        },
        computed: {
            ...mapGetters(['client', 'branches'])
        },
        methods: {
            fetchData: async function () {
                const res = await this.client.apis['bank-manager-dashboard'].getBankBranchesUsingGET();
                return JSON.parse(res.data)
            },
            assignEmployeeForm: function (props) {
                this.$modal.open({
                    parent: this,
                    component: AddEmployeeForm,
                    props: props,
                    hasModalCard: true,
                })
            },
            ...mapMutations([SET_BRANCHES])
        },
        created: async function () {
            this[SET_BRANCHES] (await this.fetchData())
        }
    }
</script>
