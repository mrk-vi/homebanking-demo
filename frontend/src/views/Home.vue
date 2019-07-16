<template>
  <div class="home">
    <div class="hero is-primary is-bold">
      <div class="hero-body">
        <div class="container">
          <p class="title">
            Your bank at home
          </p>
        </div>
      </div>
    </div>
    <BanksList/>
  </div>
</template>

<script>
import BanksList from '@/components/BanksList.vue'
import { publicClient } from '@/api'

export default {
  name: 'home',
  components: {
    BanksList
  },
  data: () => {
    return {
      banks: {}
    }
  },
  methods: {
    fetchData: async function () {
      const client = await publicClient()
      const banks = await client.apis['visitor-controller'].getBanksUsingGET_1()
      return banks.data
    }
  },
  created: async function () {
    this.banks = await this.fetchData()
  }
}
</script>
