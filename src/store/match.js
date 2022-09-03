const matchDataLocal = localStorage.getItem("matchData");
const matchData = matchDataLocal && JSON.parse(matchDataLocal);

const state = {
    matchData //比赛信息点击的比赛详情数据
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
