import {SET_USER, UNSET_USER, SET_CLIENT, UNSET_CLIENT} from './mutations-types'

export default {
    [SET_USER](state, payload) {
        state.user = payload
        state.auth = true
    },

    [UNSET_USER](state) {
        state.user = {}
        state.auth = false
    },

    [SET_CLIENT](state, payload) {
        state.client = payload
    },

    [UNSET_CLIENT](state) {
        state.client = {}
    },
}