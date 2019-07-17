import Vue from 'vue'
import Vuex from 'vuex'
import createLogger from 'vuex/dist/logger'
import getters from './getters'
import actions from './actions'
import mutations from './mutations'

Vue.use(Vuex)

const state = {
    user: {},
    auth: false,
    client: {}
}

export default new Vuex.Store({
    state: state,
    getters,
    actions,
    mutations,
    plugins: process.env.NODE_ENV !== 'production' ? [createLogger()] : [],
    strict: process.env.NODE_ENV !== 'production'
})