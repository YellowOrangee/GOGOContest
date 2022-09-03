<template>
  <div>
    <el-container>
      <!-- 左侧 -->
      <el-aside width="509px" style="height: 1000px;">
        <el-row class="search">
          <el-col :span="16" :offset="1">
            <div>
              <el-input placeholder="请输入内容" v-model="content" clearable></el-input>
            </div>
          </el-col>
          <el-button type="primary" icon="el-icon-search">搜索</el-button>
        </el-row>

        <!-- 比赛 -->
        <el-row>
          <el-col :span="7" :offset="1" class="title1">配置比赛信息</el-col>
        </el-row>

        <el-row>
          <el-col :span="5" :offset="1" class="time1">报名时间：</el-col>
          <el-col :span="3">
            <div class="datePicker">
              <el-date-picker
                v-model="value1"
                type="daterange"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                size="mini"
              >
              </el-date-picker>
            </div>
          </el-col>
        </el-row>

        <el-row justify="space-around" class="menu">
          <el-col :span="6" :offset="5" class="a">
            <div>
              <el-dropdown size="small" split-button type="primary">
                竞赛类型
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item>全部</el-dropdown-item>
                  <el-dropdown-item>工科</el-dropdown-item>
                  <el-dropdown-item>理科</el-dropdown-item>
                  <el-dropdown-item>文科</el-dropdown-item>
                  <el-dropdown-item>商科</el-dropdown-item>
                  <el-dropdown-item>其他</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </div>
          </el-col>

          <el-col :span="6" :offset="0">
            <div>
              <el-dropdown size="small" split-button type="primary">
                竞赛级别
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item>全部</el-dropdown-item>
                  <el-dropdown-item>校级</el-dropdown-item>
                  <el-dropdown-item>市级</el-dropdown-item>
                  <el-dropdown-item>省级</el-dropdown-item>
                  <el-dropdown-item>全国性</el-dropdown-item>
                  <el-dropdown-item>全球性</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </div>
          </el-col>
        </el-row>
        <!--组队  -->
        <el-row>
          <el-col :span="7" :offset="1" class="title2">配置组队信息</el-col>
        </el-row>

        <el-row class="information">
          <el-col :span="5" :offset="1" class="time1">报名时间：</el-col>
          <el-col :span="3">
            <div class="datePicker">
              <el-date-picker
                v-model="value2"
                type="daterange"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                size="mini"
              >
              </el-date-picker>
            </div>
          </el-col>
        </el-row>

        <el-row justify="space-around" class="menu">
          <el-col :span="6" :offset="5" class="a">
            <div>
              <el-dropdown size="small" split-button type="primary">
                学历要求
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item>本科</el-dropdown-item>
                  <el-dropdown-item>研究生</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </div>
          </el-col>

          <el-col :span="6" :offset="0">
            <div>
              <el-dropdown size="small" split-button type="primary">
                竞赛级别
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item>全部</el-dropdown-item>
                  <el-dropdown-item>校级</el-dropdown-item>
                  <el-dropdown-item>市级</el-dropdown-item>
                  <el-dropdown-item>省级</el-dropdown-item>
                  <el-dropdown-item>全国性</el-dropdown-item>
                  <el-dropdown-item>全球性</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </div>
          </el-col>
        </el-row>
      </el-aside>

      <!-- 右侧 -->
      <el-main style="height: 1000px;">
        <el-row>
          <el-col :span="4" :offset="19">
            <el-button type="text" @click="toReleaseTeam">
              <i class="el-icon-circle-plus releaseTeamIcon">我要发布组队信息</i>
            </el-button>
          </el-col>
        </el-row>
        <el-row class="mainContainer" v-for="(item, index) in list" :key="index">
          <el-col :span="22" :offset="1">
            <el-card class="box-card">
            <div id="teamList" class="box-card-content" @click="toTeamDetails(index)">
              <img :src="require('../assets/'+src)" alt="">
              <div class="main1">
                <span>{{item.t_name}}</span>
                <div class="content">
                  <b>比赛：</b>{{item.t_gname}}
                  <br>
                  <b>队长：</b>{{item.t_captain}}
                  <br>
                  <b>队伍需求：</b>{{item.t_demand}}
                  <br>
                  <b>类型：</b>{{item.t_type}}
                  <b class="team_small_title">创建时间：</b>{{item.t_ctime}}
                </div>
                </div>
            </div>
            </el-card>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-pagination
              layout="prev, pager, next"
              :total="total"
              background
              :page-size="pageSize"
              @current-change="toPrePage">
            </el-pagination>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import { showAllForum } from '@/api';
import router from '@/router';
export default {
  name: "ForumView",
  data() {
    return {
      content:"",
      value1: "",
      value2: "",
      src:'zt.jpg',
      total:1,    // 总条目数
      pageSize:4, // 每页的数量
      list:[],    // 队伍信息
    };
  },
  methods: {
    // 更新队伍列表
    upTeamData(num){
      this.delay(()=>{
        // 执行代码
        showAllForum(num).then((res)=>{
          // console.log("所有队伍的信息：",res);
          this.total=res.total; //总条目数
          this.pageSize=res.pageSize; //每页的数量
          this.list=res.list; //队伍信息
        })
      },500)
    },
    //加载当前页的队伍列表
    toPrePage(nowPage){
      this.upTeamData(nowPage);
    },
    // 跳转队伍详情信息页面
    toTeamDetails(val){
      console.log("队伍序号",val);
      this.$store.state.team.teamData=this.list[val]; // 队伍详情数据写入store
      localStorage.setItem(           // 队伍详情数据写入本地储存，防止刷新就没
        "teamData",
        JSON.stringify(this.list[val])
      );

      router.push({path:"/teamDetails"});
    },
    toReleaseTeam(){
      router.push({path:"/releaseTeam"})
    },
  },
  mounted() {
    this.upTeamData(1); // 加载第一页的队伍列表
  },
};
</script>

<style>
.el-aside {
  height: 900px;
  line-height: 20px;
  background-color: rgba(255, 255, 255, 100);
  text-align: center;
  overflow-x: hidden; 
}
.search {
  padding: 54px 0 39px 0;
}
.title1 {
  height: 35px;
  color: rgba(16, 16, 16, 100);
  font-size: 24px;
  text-align: left;
  font-family: SourceHanSansSC-bold;
  font-weight: bold;
}
.time1 {
  width: 90px;
  height: 30px;
  color: rgba(113, 107, 107, 100);
  font-size: 18px;
  text-align: left;
  font-family: SourceHanSansSC-bold;
  line-height: 30px;
}
.menu {
  /* background-color: aqua; */
  padding-top: 64px;
}
.title2 {
  padding-top: 200px;
  height: 35px;
  color: rgba(16, 16, 16, 100);
  font-size: 24px;
  text-align: left;
  font-family: SourceHanSansSC-bold;
  font-weight: bold;
}
.information {
  padding-top: 50px;
}
.el-main {
  background-color: #efeff4;
  width: 931px;
}
.releaseTeamIcon {
  font-size: 16px;
}
.el-main .mainContainer{
  padding: 20px 0;
}
.el-main img{
  width: 137px;
  height: 109px;
}
.el-main .box-card-content{
  display: flex;
}
.box-card-content span{
  width: 32px;
  height: 16px;
  color: rgba(96, 98, 102, 100);
  font-size: 16px;
  text-align: left;
  font-family: Helvetica-regular;
}
.box-card-content .main1{
  padding: 0 20px ;
}
.content{
  padding-top: 7px;
  width: 573px;
  height: 64px;
  color: rgba(144, 147, 153, 100);
  font-size: 14px;
  text-align: left;
  font-family: Helvetica-regular;
}
#teamList{
  cursor: pointer;
}
.team_small_title{
  padding-left: 20px;
}
</style>

