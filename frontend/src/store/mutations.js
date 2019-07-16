import { SET_USER, UNSET_USER } from './mutations-types'

export default {
    [SET_USER] (state, payload) {
        state.user = payload
        state.auth = true
    },
    
    [UNSET_USER] (state) {
        state.user = {}
        state.auth = false
    }
}