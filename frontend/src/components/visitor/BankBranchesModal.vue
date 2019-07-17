<template>
    <div class="modal-card" style="width: auto">
        <div class="modal-card-head">
            <header>{{name}} Branches</header>
        </div>
        <section class="modal-card-body">
            <b-table :data="bankBranches">
                <template slot-scope="branch">
                    <b-table-column label="#id">
                        {{branch.row.id}}
                    </b-table-column>
                    <b-table-column field="name" label="Name">
                        {{branch.row.name}}
                    </b-table-column>
                    <b-table-column field="address" label="Address">
                        {{branch.row.address}}
                    </b-table-column>
                    <b-table-column field="opening" label="Opening">
                        {{branch.row.opening}}
                    </b-table-column>
                    <b-table-column field="closing" label="Closing">
                        {{branch.row.closing}}
                    </b-table-column>
                    <b-table-column label="Action">
                        <b-button @click="requestForm(branch.row)">Subscribe</b-button>
                    </b-table-column>
                </template>
            </b-table>
        </section>
    </div>
</template>

<script>
    import BTableColumn from "buefy/src/components/table/TableColumn";
    import BButton from "buefy/src/components/button/Button";
    import RegistrationFormModal from './RegistrationFormModal.vue'

    export default {
        name: "BankBranchesModal",
        components: {BButton, BTableColumn, RegistrationFormModal},
        props: ['name', 'bankBranches'],
        data() {
            return {

            }
        },
        methods: {
            requestForm: function (props) {
                this.$modal.open({
                    parent: this.$parent,
                    component: RegistrationFormModal,
                    props: props,
                    hasModalCard: true,
                    onCancel: this.$parent.close()
                })
            }
        }
    }
</script>

