export default {

    user: state => state.user,

    auth: state => state.auth,

    client: state => state.client,

    isAdmin: state => {
        try {
            return !!state.user.authorities.find(item => item === "ADMIN")
        } catch (err) {
            return false
        }
    },

    isManager: state => {
        try {
            return !!state.user.authorities.find(item => item === "MANAGER")
        } catch (err) {
            return false
        }
    },

    isEmployee: state => {
        try {
            return !!state.user.authorities.find(item => item === "EMPLOYEE")
        } catch (err) {
            return false
        }
    },

    isCustomer: state => {
        try {
            return !!state.user.authorities.find(item => item === "CUSTOMER")
        } catch (err) {
            return false
        }
    },
}