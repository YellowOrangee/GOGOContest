const state = {
    teamData:{}  //组队论坛点击的组队信息
}
const mutations = {};
const actions = {};
const getters = {
    //获取组队论坛点击的组队信息
    getTeamData(state){
        return state.teamData;
    }
};
export default {
    state,
    getters,
    mutations,
    actions,
};
