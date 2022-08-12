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
    if (result.code==200) {
      commit('GETUSERINFO',result.data)
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
