import Vue from 'vue'
import Vuex from 'vuex'
import user from './user'
import team from './team'
import match from './match'

Vue.use(Vuex)

export default new Vuex.Store({
  state:{},
  getters:{},
  mutations:{},
  actions: {
  },
  modules: {
    user,team,match
  }
})
