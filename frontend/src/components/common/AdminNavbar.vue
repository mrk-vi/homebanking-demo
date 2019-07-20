<template>
    <div class="navbar-item has-dropdown is-hoverable">
        <a class="navbar-link">
            Administration
        </a>

        <div class="navbar-dropdown">
            <a class="navbar-item" @click="isCreateBankFormModalActive = true">
                Create Bank
            </a>
            <a class="navbar-item" @click="isUnauthorizedListModalActive = true">
                Show Registration Requests
            </a>
        </div>

        <b-modal :active.sync="isCreateBankFormModalActive" has-modal-card>
            <CreateBankForm/>
        </b-modal>

        <b-modal :active.sync="isUnauthorizedListModalActive" has-modal-card>
            <UnauthorizedList/>
        </b-modal>
    </div>
</template>

<script>
    import CreateBankForm from '../administration/CreateBankForm.vue'
    import UnauthorizedList from '../administration/UnauthorizedList.vue'
    import BModal from "buefy/src/components/modal/Modal";

    export default {
        name: "AdminNavbar",
        data: () => {
            return {
                isCreateBankFormModalActive: false,
                isUnauthorizedListModalActive: false
            }
        },
        components: {
            BModal,
            CreateBankForm, UnauthorizedList
        }
    }
</script>

<style scoped>

</style>