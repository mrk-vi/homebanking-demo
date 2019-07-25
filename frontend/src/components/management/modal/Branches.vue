<template>
    <div class="modal-card" style="width: auto">
        <div class="modal-card-head">
            <header>Branches</header>
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
    import { mapGetters } from 'vuex'
    import AddEmployeeForm from './AddEmployeeForm.vue'
    export default {
        name: "Branches",
        data(){
            return {
                branches: []
            }
        },
        computed: {
            ...mapGetters(['client'])
        },
        methods: {
            fetchData: async function () {
                const res = await this.client.apis['bank-manager-dashboard'].getBankBranchesUsingGET();
                return JSON.parse(res.data)
            },
            assignEmployeeForm: function (props) {
            this.$modal.open({
                parent: this.$parent,
                component: AddEmployeeForm,
                props: props,
                hasModalCard: true,
                onCancel: this.$parent.close()
            })
            }
        },
        created: async function () {
            this.branches = await this.fetchData()
        }
    }
</script>
