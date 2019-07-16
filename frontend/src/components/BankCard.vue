<template>
  <div class="card">
    <div class="card-image">
      <figure class="image is-4by3">
        <img :src="imageUrl" alt="Placeholder image">
      </figure>
    </div>
    <div class="card-content">
      <div class="media">
        <div class="media-content">
          <p class="title is-4">{{bank.name}}</p>
          <p class="subtitle is-6">{{bank.address}}</p>
        </div>
        <div class="media-right">
          <p><button class="button" @click="isDetailsModalActive = true">Details</button></p>
          <p><button class="button" @click="isBranchModalActive = true">Branches</button></p>
        </div>
      </div>
      <div class="content">
        {{bank.description}}
      </div>
    </div>
    <b-modal :active.sync="isBranchModalActive">
      <b-table :data="bank.bankBranches" :columns="columns"></b-table>
    </b-modal>
    <b-modal :active.sync="isDetailsModalActive">
      <BankDetailsModal v-bind="bank" />
    </b-modal>
  </div>
</template>

<script>
import BankDetailsModal from './BankDetailsModal.vue'

export default {
  name: 'BankCard',
  props: { bank: {} },
  components: {
    BankDetailsModal
  },
  data: () => {
    return {
      firstImage: '',
      isBranchModalActive: false,
      isDetailsModalActive: false,
      columns: [
        {
          field: 'name',
          label: 'Name'
        },
        {
          field: 'address',
          label: 'Address'
        },
        {
          field: 'opening',
          label: 'Opening'
        },
        {
          field: 'closing',
          label: 'Closing'
        },
        
      ]
    }

  },
  computed: {
    imageUrl: function () {
      try {
        return "http://localhost:8080/image/"+this.bank.images[0].id
      } catch (err) {
        return "https://bulma.io/images/placeholders/1280x960.png" 
      }
    }
  }
}
</script>

<style>

</style>
