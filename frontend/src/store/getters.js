export default {

    user: state => state.user,

    auth: state => state.auth,

    isAdmin: state => { 
        try {
            return state.user.authorities.find(item => item==="ADMIN")?true:false
        } catch (err) {
            return false
        }
    },
    
    isManager: state => { 
        try {
            return state.user.authorities.find(item => item==="MANAGER")?true:false
        } catch (err) {
            return false
        }
    },
    
    isEmployee: state => { 
        try {
            return state.user.authorities.find(item => item==="EMPLOYEE")?true:false
        } catch (err) {
            return false
        }
    },
}