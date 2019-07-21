<template>
    <form action="">
        <div class="modal-card" style="width: 680px">
            <header class="modal-card-head">
                <p class="modal-card-title">Interest rate</p>
            </header>
            <section class="modal-card-body">
                <b-field label="Interest Rate (in %)">
                    <b-numberinput v-model="form.interestRate"  controls-position="compact" step="0.05" min="0" max="3"></b-numberinput>
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
    import {mapGetters} from "vuex";

    export default {
        name: "InterestRateForm",
        props: ['id', 'interestRate'],
        data() {
            return {
                form: {
                    interestRate: this.interestRate
                }
            }
        },
        computed: {
          ...mapGetters(['client'])
        },
        methods: {
            submit: async function (evt) {
                evt.preventDefault()
                await this.client.apis['bank-manager-dashboard'].setInterestRateUsingPUT({id:this.id, form:this.form})
                this.$parent.close()
            }
        }
    }
</script>

<style scoped>

</style>