<template>
    <div class="navbar-item has-dropdown is-hoverable">
        <a class="navbar-link">
            My Accounts
        </a>

        <div class="navbar-dropdown">
            <a class="navbar-item" @click="isDepositModalActive = true">
                Deposit
            </a>
            <a class="navbar-item" @click="isWithdrawModalActive = true">
                Withdraw
            </a>
            <a class="navbar-item" @click="isTransferModalActive = true">
                Transfer
            </a>
            <a class="navbar-item" @click="isRefillModalActive = true">
                Refill Mobile
            </a>
            <a class="navbar-item" @click="isCarTaxModalActive = true">
                Car Tax
            </a>
            <a class="navbar-item" @click="isBankingOperationsModalActive = true">
                Banking Operations list
            </a>
        </div>

        <b-modal :active.sync="isDepositModalActive" has-modal-card>
            <deposit></deposit>
        </b-modal>
        <b-modal :active.sync="isWithdrawModalActive" has-modal-card>
            <withdraw></withdraw>
        </b-modal>
        <b-modal :active.sync="isTransferModalActive" has-modal-card>
            <transfer></transfer>
        </b-modal>
        <b-modal :active.sync="isRefillModalActive" has-modal-card>
            <refill></refill>
        </b-modal>
        <b-modal :active.sync="isCarTaxModalActive" has-modal-card>
            <car-tax></car-tax>
        </b-modal>
        <b-modal :active.sync="isBankingOperationsModalActive" has-modal-card>
            <banking-operation-list></banking-operation-list>
        </b-modal>
    </div>
</template>

<script>
    import Deposit from "../customer/modal/Deposit";
    import Withdraw from "../customer/modal/Withdraw";
    import Transfer from "../customer/modal/Transfer";
    import Refill from "../customer/modal/Refill";
    import CarTax from "../customer/modal/CarTax";
    import BankingOperationList from "../customer/modal/BankingOperationList";
    export default {
        name: "CustomerNavbar",
        components: {BankingOperationList, CarTax, Refill, Transfer, Withdraw, Deposit},
        data() {
            return {
                isDepositModalActive: false,
                isWithdrawModalActive: false,
                isTransferModalActive: false,
                isRefillModalActive: false,
                isCarTaxModalActive: false,
                isBankingOperationsModalActive: false
            }
        }
    }
</script>

<style scoped>

</style>