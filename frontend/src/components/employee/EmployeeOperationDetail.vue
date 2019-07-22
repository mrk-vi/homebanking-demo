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
    import {mapGetters} from "vuex";
    export default {
        name: "OperationDetail",
        components: {BField},
        props: ['id', 'operationType', 'operationState', 'amount', 'bankAccount', 'recipientIban'],
        computed: {
            ...mapGetters(['client'])
        },
        methods:{
            authorize: async function () {
                await this.client.apis['employee-dashboard'].authorizeBankingOperationUsingPUT({id: this.id})
                this.$parent.close()
            },
            negate: async function () {
                await this.client.apis['employee-dashboard'].negateBankingOperationUsingPUT({id: this.id})
                this.$parent.close()
            }
        }
    }
</script>

<style scoped>

</style>