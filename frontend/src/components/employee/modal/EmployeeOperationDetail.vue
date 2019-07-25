<template>
    <form action="">
        <div class="modal-card" >
            <header class="modal-card-head">
                <p class="modal-card-title">Operation {{id}} Detail</p>
            </header>
            <section class="modal-card-body">
                <label><strong>Operation</strong></label>
                <b-field grouped>
                    <b-field label="Type">
                        {{operationType}}
                    </b-field>
                    <b-field label="State">
                        {{operationState}}
                    </b-field>
                    <b-field label="Amount">
                        {{amount}}€
                    </b-field>
                    <b-field v-show="operationType === 'TRANSFER'" label="Recipient">
                        {{recipientIban}}
                    </b-field>
                </b-field>
                <label><strong>Bank Account</strong></label>
                <b-field grouped>
                    <b-field label="IBAN">
                        {{bankAccount.iban}}
                    </b-field>
                    <b-field label="Balance">
                        {{bankAccount.balance}}
                    </b-field>
                </b-field>

            </section>
            <footer class="modal-card-foot">
                <button class="button" type="button" @click="$parent.close()">Close</button>
                <button v-show="operationState === 'OPEN'"
                        class="button is-success"
                        @click="authorize">Authorize</button>
                <button v-show="operationState === 'OPEN'"
                        class="button is-danger"
                        @click="negate">Negate</button>

            </footer>
        </div>
    </form>
</template>

<script>
    import BField from "buefy/src/components/field/Field";
    import {mapGetters, mapMutations} from "vuex";
    import {SET_OPERATIONS} from "../../../store/mutations-types";
    export default {
        name: "OperationDetail",
        components: {BField},
        props: ['id', 'operationType', 'operationState', 'amount', 'bankAccount', 'recipientIban'],
        computed: {
            ...mapGetters(['client', 'operations'])
        },
        methods:{
            authorize: async function (evt) {
                evt.preventDefault()
                await this.client.apis['employee-dashboard'].authorizeBankingOperationUsingPUT({id: this.id})
                const operations = this.operations.filter( op => op.id !== this.id )
                this[SET_OPERATIONS](operations)
                this.$parent.close()
            },
            negate: async function (evt) {
                evt.preventDefault()
                await this.client.apis['employee-dashboard'].negateBankingOperationUsingPUT({id: this.id})
                const operations = this.operations.filter( op => op.id !== this.id )
                this[SET_OPERATIONS](operations)
                this.$parent.close()
            },
            ...mapMutations([SET_OPERATIONS])
        }
    }
</script>

<style scoped>

</style>