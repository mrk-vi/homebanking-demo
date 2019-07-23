<template>
    <div class="modal-card" style="width: auto">
        <div class="modal-card-head">
            <header>Operations list</header>
        </div>
        <section class="modal-card-body">
            <form>
                <b-field grouped>
                    <b-field label="State">
                        <b-select v-model="filter.state">
                            <option>OPEN</option>
                            <option>REJECTED</option>
                            <option>AUTHORIZED</option>
                        </b-select>
                    </b-field>
                    <b-field label="Type">
                        <b-select v-model="filter.type">
                            <option>DEPOSIT</option>
                            <option>WITHDRAW</option>
                            <option>TRANSFER</option>
                            <option>REFILL</option>
                            <option>CAR_TAX</option>
                            <option>INTERESTS</option>
                        </b-select>
                    </b-field>
                </b-field>
                <label><strong>Date</strong></label>
                <b-field grouped>
                    <b-field label="From">
                        <b-datepicker
                                v-model="filter.fromDay"
                                icon="calendar-range"></b-datepicker>
                    </b-field>
                    <b-field label="To">
                        <b-datepicker
                                v-model="filter.toDay"
                                icon="calendar-range"></b-datepicker>
                    </b-field>
                </b-field>
                <label><strong>Amount</strong></label>
                <b-field grouped>
                    <b-field label="From">
                        <b-numberinput
                                step="0.01"
                                v-model="filter.fromAmount"
                                controls-position="compact" icon="currency-eur"></b-numberinput>
                    </b-field>
                    <b-field label="To">
                        <b-numberinput
                                step="0.01"
                                v-model="filter.toAmount"
                                controls-position="compact" icon="currency-eur"></b-numberinput>
                    </b-field>
                </b-field>
                <b-field grouped>
                    <p class="control">
                        <button
                                class="button is-primary"
                                @click="search">Search</button>
                    </p>
                    <p class="control">
                        <button
                                class="button is-success"
                                @click="exportPDF">Export PDF</button>
                    </p>
                </b-field>
            </form>

            <b-table :data="operations">
                <template slot-scope="operation">
                    <b-table-column label="#id">
                        {{operation.row.id}}
                    </b-table-column>
                    <b-table-column label="Amount">
                        {{operation.row.amount}}
                    </b-table-column>
                    <b-table-column label="Iban">
                        {{operation.row.bankAccount.iban}}
                    </b-table-column>
                    <b-table-column label="Action">
                        <b-button @click="details(operation.row)">Details</b-button>
                    </b-table-column>
                </template>
            </b-table>
        </section>
        <footer class="modal-card-foot">
            <button class="button" type="button" @click="$parent.close()">Close</button>
        </footer>
    </div>
</template>

<script>
    import {mapGetters} from "vuex";
    import BField from "buefy/src/components/field/Field";
    import BSelect from "buefy/src/components/select/Select";
    import BDatepicker from "buefy/src/components/datepicker/Datepicker";
    import BNumberinput from "buefy/src/components/numberinput/Numberinput";
    import OperationDetail from "./EmployeeOperationDetail";

    export default {
        name: "EmployeeOperationsList",
        components: {BNumberinput, BDatepicker, BSelect, BField, OperationDetail},
        data() {
            return {
                filter: {
                    fromAmount: null,
                    toAmount: null,
                    fromDay: null,
                    toDay: null,
                    state: null,
                    type: null
                },
                operations: []
            }
        },
        computed: {
            ...mapGetters(['client'])
        },
        methods:{
            parseFilter: (filter) => {
                try {
                    filter.toDay.setHours(filter.toDay.getHours()+24)

                    const parsed = {
                        ...filter,
                        fromDay: filter.fromDay.toJSON(),
                        toDay: filter.toDay.toJSON()
                    }

                    filter.toDay.setHours(filter.toDay.getHours()-24)
                    return parsed
                } catch (e) {
                    return filter
                }
            },
            search: async function(evt) {
                evt.preventDefault()
                const res = await this.client.apis['employee-dashboard'].getBankingOperationsByEmployeeUsingPOST({filter: this.parseFilter(this.filter)})
                this.operations = JSON.parse(res.data)
            },
            exportPDF: async function(evt){
                evt.preventDefault()
                const res = await this.client.apis['pdf-controller'].exportBankingOperationsPDFUsingPOST({filter: this.parseFilter(this.filter)})

                // HANDLING OF BLOB

                const fileURL = URL.createObjectURL(res.data)

                // hack that works on the most common browsers
                var link = document.createElement('a');
                link.href = fileURL;
                link.download="operations.pdf";
                link.click();
                setTimeout(function(){
                    // For Firefox it is necessary to delay revoking the ObjectURL
                    window.URL.revokeObjectURL(fileURL);
                }, 100);
            },
            details: function (operation) {
                this.$modal.open({
                    parent: this.$parent,
                    component: OperationDetail,
                    props: operation,
                    hasModalCard: true,
                    //onCancel: this.$parent.close()
                })
            }
        }
    }
</script>

<style scoped>

</style>