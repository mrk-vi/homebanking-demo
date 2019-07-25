<template>
    <div class="navbar-item has-dropdown is-hoverable">
        <a class="navbar-link">
            Operations
        </a>

        <div class="navbar-dropdown">
            <a class="navbar-item" @click="isOperationListModalActive = true">
                Operations List
            </a>
        </div>


        <b-modal :active.sync="isOperationListModalActive" has-modal-card>
            <employee-operations-list></employee-operations-list>
        </b-modal>
    </div>
</template>

<script>
    import EmployeeOperationsList from "../employee/modal/EmployeeOperationList";
    export default {
        name: "EmployeeNavbar",
        components: {EmployeeOperationsList},
        data() {
            return {
                isOperationListModalActive: false
            }
        }
    }
</script>

<style scoped>

</style>