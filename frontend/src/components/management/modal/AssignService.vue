<template>
    <form action="">
        <div class="modal-card">
            <header class="modal-card-head">
                <p class="modal-card-title">Create Bank Product</p>
            </header>
            <section class="modal-card-body">
                <div  v-for="service in activables" v-bind:key="service"  class="field">
                    <b-checkbox v-model="requested" :native-value="service">{{service}}</b-checkbox>
                </div>
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
    import BCheckbox from "buefy/src/components/checkbox/Checkbox";

    export default {
        name: "AssignService",
        components: {BCheckbox},
        data(){
          return {
              services: [],
              activables: [],
              requested: [],
              all: [
                  "REFILL",
                  "CAR_TAX"
              ]
          }
        },
        computed: {
            ...mapGetters(['client'])
        },
        methods: {
            fetchData: async function () {
                const res = await this.client.apis['bank-manager-dashboard'].getBankServicesByManagerUsingGET()
                return JSON.parse(res.data)
            },
            submit: async function (evt) {
                evt.preventDefault()
                const client = this.client
                await this.requested.forEach( async function (item) {
                    await client.apis['bank-manager-dashboard'].createBankServiceUsingPOST({form: {type: item}})
                })
                this.$parent.close()
            }
        },
        created: async function () {
            this.services = await this.fetchData()
            if ( this.services.length > 0 ){
                this.activables = this.all
                    .filter( item => !this.services.flatMap( service => service.type ).includes(item) )
            }
            else
                this.activables = [...this.all]
        }

    }
</script>

<style scoped>

</style>