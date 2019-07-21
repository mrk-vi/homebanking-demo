<template>
    <div class="navbar-item has-dropdown is-hoverable">
        <a class="navbar-link">
            Management
        </a>

        <div class="navbar-dropdown">
            <a class="navbar-item" @click="isCreateBankBranchModalActive = true">
                Create Bank Branch
            </a>
            <a class="navbar-item" @click="isCreateBankProductModalActive = true">
                Create Bank Product
            </a>
            <a class="navbar-item" @click="isAssignBankServiceModalActive = true">
                Add Service
            </a>
            <a class="navbar-item" @click="isBankBranchesModalActive = true">
                Show Branches
            </a>
            <a class="navbar-item" @click="isBankProductsModalActive = true">
                Show Products
            </a>
        </div>

        <b-modal :active.sync="isCreateBankBranchModalActive" has-modal-card>
            <CreateBankBranch/>
        </b-modal>

        <b-modal :active.sync="isCreateBankProductModalActive" has-modal-card>
            <CreateBankProduct/>
        </b-modal>

        <b-modal :active.sync="isAssignBankServiceModalActive" has-modal-card>
            <AssignService/>
        </b-modal>

        <b-modal :active.sync="isBankBranchesModalActive" has-modal-card>
            <Branches/>
        </b-modal>

        <b-modal :active.sync="isBankProductsModalActive" has-modal-card>
            <BankProducts/>
        </b-modal>
    </div>
</template>

<script>
    import CreateBankBranch from '../management/CreateBankBranch.vue'
    import CreateBankProduct from '../management/CreateBankProduct.vue'
    import AssignService from '../management/AssignService.vue'
    import Branches from '../management/Branches.vue'
    import BankProducts from '../management/BankProducts.vue'
    import BModal from "buefy/src/components/modal/Modal";

    export default {
        name: "ManagerNavbar",
        data: () => {
            return {
                isCreateBankBranchModalActive: false,
                isCreateBankProductModalActive: false,
                isAssignBankServiceModalActive: false,
                isBankBranchesModalActive: false,
                isBankProductsModalActive: false
            }
        },
        components: {
            BModal,
            CreateBankProduct,
            CreateBankBranch,
            AssignService,
            Branches,
            BankProducts
        }
    }
</script>

<style scoped>

</style>