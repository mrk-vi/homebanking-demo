<template>
    <div id="banks-list">
        <div class="section content is-medium">
            <div class="title has-text-centered">
                <h3>These are our banks</h3>
            </div>
            <div class="columns">
                <BankCard v-for="bank in banks" v-bind:bank="bank" v-bind:key="bank.id" class="column"/>
            </div>
        </div>
    </div>
</template>


<script>
    import {publicClient} from '../../service'
    import BankCard from './BankCard.vue'

    export default {
        name: 'BanksList',
        components: {
            BankCard
        },
        data: () => {
            return {
                banks: []
            }
        },
        methods: {
            fetchData: async function () {
                const client = await publicClient()
                const res = await client.apis['visitor-controller'].getBanksUsingGET()
                return JSON.parse(res.data)
            }
        },
        created: async function () {
            this.banks = await this.fetchData()
        }
    }
</script>

