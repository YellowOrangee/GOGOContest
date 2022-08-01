// import * as types from './mutation-types'

const mutations = {
    SWITCH_LOGIN_STATE(state) {
        state.checkLogin=!state.checkLogin;
    }
}

export default mutations;