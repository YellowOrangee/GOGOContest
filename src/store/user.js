const uInfoLocal = localStorage.getItem("uInfo");
const uInfo = uInfoLocal && JSON.parse(uInfoLocal);
const state = {
  uInfo,
};
const getters = {};
const mutations = {
};
const actions = {};
export default {
  state,
  getters,
  mutations,
  actions,
};
