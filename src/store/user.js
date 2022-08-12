const uInfoLocal = localStorage.getItem("uInfo");
const uInfo = uInfoLocal && JSON.parse(uInfoLocal);
import {showInfo} from '@/api'
const state = {
  uInfo,
  userInfo:{
  }
};
const mutations = {
  GETUSERINFO(state,userInfo){
    state.userInfo=userInfo
  }
};
const actions = {
  //获取个人信息的action
  async getUserInfo({commit}){
    let result = await showInfo();
    console.log(result)
    if (result[0].u_id) {
      commit('GETUSERINFO',result[0])
    }
  }
};
const getters = {
};
export default {
  state,
  getters,
  mutations,
  actions,
};
