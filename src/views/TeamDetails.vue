<template>
  <div id="teamDetails">
    <!-- 顶部图片 -->
    <div class="team_details_header">
      <el-image
      style="width: 90%; height: 450px; margin: 0 auto;"
      :src="require('../assets/'+t_img)"
      fit="contain"></el-image>
    </div>
    <br><hr><br>

    <!-- 左下角 -->
    <div class="team_details_left">
      <div class="teamTitle">队伍基本信息</div>
      <hr>

      <h3>队伍名称</h3>
      <p>{{team.t_name}}</p>

      <h3>队伍简介</h3>
      <p>队长：{{team.t_captain}}</p>
      <p>队伍需求：{{team.t_demand}}</p>
      <p>创建时间：{{team.t_ctime}}</p>
      <p>参赛类型：{{team.t_type}}</p>

      <h3>联系方式</h3>
      <p>{{team.t_contact}}</p>

      <h3>补充信息</h3>
      <p>暂无</p>
      <br><br><hr>

      <!-- 比赛信息 -->
      <div class="teamTitle">比赛信息</div><hr>
      <el-row v-for="(game, index) in competition" :key="index">
        <br>
        <span class="teamApplyGames">{{game.name}}</span>
        <el-link :href="game.web" target="_blank">{{game.web}}</el-link>
        <br><br>
      </el-row>

      <hr>
      <div class="teamTitle">讨论区</div>
      <hr><br>

    </div>

    <!-- 右下角 -->
    <div class="team_details_right">
      <div v-if=!collectionStatus>
        <el-button type="primary" class="team_details_but" @click="clickCollection">点击收藏</el-button>
        <i class="el-icon-star-on"></i>
      </div>
      <div v-if=collectionStatus>
        <el-button type="primary" plain class="team_details_but" @click="clickCollection">点击取消收藏</el-button>
        <i class="el-icon-star-on" style="color: gold;"></i>
      </div>
      <br><br>
      <h3>队长</h3>
      <div class="team_status">
        <img :src="require('../assets/'+teamStatus.captain.head)" alt="头像">
        <div>{{team.t_captain}}</div>
      </div>
      <h3>队员</h3>
      <div class="team_status">
        <img :src="require('../assets/'+teamStatus.member.head)" alt="头像">
        <div>{{teamStatus.member.name}}</div>
      </div>
      <h3>发布时间</h3>
      <p>{{teamStatus.teamTime}}</p>
      <br><br>
      <el-col :span="8" :offset="8">
        <el-button type="primary" @click="applyThisTeam">申请加入该队伍</el-button>
      </el-col>
      
    </div>
  </div>
</template>

<script>
export default {
  name: "TeamDetails",
  data() {
    return{
      // 队伍基本信息
      t_img:'zt.jpg',
      team:{
        t_name:"队伍名",
        t_contact:"队长联系方式",
        t_captain:"队长",
        t_demand:"队伍需求",
        t_ctime:"创建时间",
        t_type:"参赛类型",
        t_img:'zt.jpg',
      },
      // 比赛信息
      competition:[
        {
        img: 'zt.jpg',
        name: "中国国际“互联网+”大学生创新创业大赛",
        introduction: "示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字",
        supplement: "无",
        web: "https://cy.ncss.cn/",
        },{
        img: 'zt.jpg',
        name: "中国国际“互联网+”大学生创新创业大赛",
        introduction: "示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字",
        supplement: "无",
        web: "https://cy.ncss.cn/",
        }],
      // 发布信息
      teamStatus:{
        captain:{
          head: "zt.jpg",       // 头像
        },
        member:{
          head: "zt.jpg",       // 头像
          name: "汪汪大队",   // 发布者
        },
        teamTime: "2022-08-11 13:24:48",        // 比赛时间
      },
      collectionStatus: false,//收藏
    }
  },
  methods: {
    clickCollection() {
      this.delay(()=>{
        this.collectionStatus=!this.collectionStatus;
      },500)
    },
    // 申请加入该队伍
    applyThisTeam(){}
  },
  mounted() {
    this.team=this.$store.state.team.teamData;
  },
}
</script>

<style>
/* 比赛详情页面 */
#teamDetails {
  width: 1440px;
  margin-top: 40px;
}
#teamDetails h3,.teamApplyGames {
  border-left: 5px solid #2F8FFB;
  padding-left: 10px;
  margin-left: 60px;
}
#teamDetails p {
  padding-left: 75px;
  width: 90%;
}
.teamTitle {
  font: 32px 庞门正道粗书体;
  margin-left: 60px;
}
/* 顶部的图片 */
.team_details_header {
  display: flex;
}
/* 左下角 */
.team_details_left {
  width: 68%;
  padding-left: 30px;
  float: left;
}
.team_details_left .el-link {
  margin-left: 20px;
}
/* 右下角 */
.team_details_right {
  width: 26%;
  float: right;
  padding-right: 20px
}
/* 收藏按钮 */
.team_details_but {
  float: right;
}
.team_details_right i {
  font-size: 30px;
  float: right;
  padding-top: 5px;
}
.team_details_right img {
  width: 36px;
  height: 36px;
  border-radius: 100px;
}
/* 右下角-队伍状态 */
.team_status {
  height: 36px;
  padding-left: 60px;
}
/* 右下角-队伍状态-头像 */
.team_status img {
  float: left;
  margin: 0 15px 0 15px;
}
/* 右下角-发布者-昵称 */
.team_status div {
  float: left;
  line-height: 36px;
}
</style>