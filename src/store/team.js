const teamDataLocal = localStorage.getItem("teamData");
const teamData = teamDataLocal && JSON.parse(teamDataLocal);

const state = {
    teamData  //组队论坛点击的组队信息
}
const mutations = {};
const actions = {};
const getters = {};
export default {
    state,
    getters,
    mutations,
    actions,
};
