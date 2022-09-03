const matchDataLocal = localStorage.getItem("matchData");
const matchData = matchDataLocal && JSON.parse(matchDataLocal);
import {searchByCondition,searchByPage} from '@/api'

const state = {
    matchData, //比赛信息点击的比赛详情数据
    list:[],//比赛信息列表
}
const mutations = {
    GETMATCHLIST(state,list){
        state.list=list;
    }
};
const actions = {
    //根据信息搜索比赛
    async getMatchListByCondition({commit},data){
        console.log(data)
        let result = await searchByCondition(data);
        console.log(result)
    commit('GETMATCHLIST',result[0])
    },
    //根据页码搜索比赛
    async getMatchListByPage({commit},data){
        console.log(data)
        let result = await searchByPage(data);
        console.log(result)
    commit('GETMATCHLIST',result[0])
    },
};
const getters = {};
export default {
    state,
    getters,
    mutations,
    actions,
};
