<template>
    <form action="">
        <div class="modal-card">
            <header class="modal-card-head">
                <p class="modal-card-title">Create Bank Product</p>
            </header>
            <section class="modal-card-body">
                <b-field label="Product Name">
                    <b-input
                            type="text"
                            v-model="form.name"
                            placeholder="Young Account"
                            required
                    >
                    </b-input>
                </b-field>
                <b-field label="Description">
                    <b-input
                            type="textarea"
                            v-model="form.description"
                            placeholder="Bank product description ..."
                    >
                    </b-input>
                </b-field>
                <b-field label="Interest Rate (in %)">
                    <b-numberinput v-model="form.interestRate"  controls-position="compact" step="0.05" min="0" max="3"></b-numberinput>
                </b-field>
                <b-field label="Type">
                    <b-select placeholder="Select a product type" required v-model="form.type">
                        <option value="ACCT">C/C (Simple Bank Account)</option>
                        <option value="PREPAID">Prepaid (Card)</option>
                    </b-select>
                </b-field>
            </section>
            <footer class="modal-card-foot">
                <button class="button" type="button" @click="$parent.close()">Close</button>
                <button class="button is-primary" type="submit" @click="submit">Submit</button>
            </footer>
        </div>
    </form>
</template>

<script>
    import { mapGetters } from 'vuex'
    import BField from "buefy/src/components/field/Field";
    import BNumberinput from "buefy/src/components/numberinput/Numberinput";
    import BSelect from "buefy/src/components/select/Select";
    export default {
        name: "CreateBankProduct",
        components: {BSelect, BNumberinput, BField},
        data() {
            return {
                form: {
                    description: '',
                    interestRate: 0,
                    name: '',
                    type: 'ACCT'
                }
            }
        },
        computed: {
            ...mapGetters(['client']),
        },
        methods: {
            submit: async function (evt) {
                evt.preventDefault()
                await this.client.apis['bank-manager-dashboard'].createBankProductUsingPOST({form: this.form})
                this.$parent.close()
            }
        }
    }
</script>
