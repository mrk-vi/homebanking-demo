import {
    SET_USER,
    UNSET_USER,
    SET_CLIENT,
    UNSET_CLIENT,
    SET_OPERATIONS,
    SET_BRANCHES,
    ADD_BRANCH, SET_BANKS, ADD_BANK
} from './mutations-types'

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

    [SET_OPERATIONS] (state, payload) {
        state.operations = payload
    },

    [SET_BRANCHES] (state, payload) {
        state.branches = payload
    },

    [ADD_BRANCH] (state, payload) {
        state.branches = [...state.branches, payload]
    },

    [SET_BANKS] (state, payload) {
        state.banks = payload
    },

    [ADD_BANK] (state, payload) {
        state.banks = [...state.banks, payload]
    }
}