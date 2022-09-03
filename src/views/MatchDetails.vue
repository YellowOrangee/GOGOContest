<template>
  <div id="matchDetails">
    <!-- 顶部图片 -->
    <div class="match_details_header">
      <el-image
      style="width: 90%; height: 450px; margin: 0 auto;"
      :src="require('../assets/'+img)"
      fit="contain"></el-image>
    </div>
    <br><hr><br>

    <!-- 左下角 -->
    <div class="match_details_left">
      <div style="font: 32px 庞门正道粗书体;text-align: center;">
        {{competition.g_title}}
        <hr>
      </div>
      <div style="margin-left: 50px;">
        <h3>比赛简介</h3>
        <p>{{competition.g_body}}</p>
        <h3>补充内容</h3>
        <p>{{supplement}}</p>
        <h3>大赛官网</h3>
        <el-link :href="competition.g_link" target="_blank">{{competition.g_link}}</el-link>
        <br><br><hr>
        <div style="font: 32px 庞门正道粗书体;">该比赛组队信息</div>
        <hr>
        <!-- 该比赛组队信息 -->
        <el-main>
          <el-row class="mainContainer" v-for="(item, index) in team" :key="index">
            <el-col :span="22" :offset="1">
              <el-card class="box-card">
                <div class="box-card-content">
                  <img :src="require('../assets/'+item.src)" alt="">
                  <div class="main1">
                    <span>{{item.title}}</span>
                    <div class="content">
                      {{item.content}}
                    </div>
                  </div>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </el-main>
        <br><hr>
        <div style="font: 32px 庞门正道粗书体;">讨论</div>
        <hr><br>

      </div>
    </div>

    <!-- 右下角 -->
    <div class="match_details_right">
      <span class="count">收藏量：{{collectionCount}}</span>
      <div v-if=!collectionStatus>
        <el-button type="primary" class="match_details_but" @click="clickCollection">点击收藏</el-button>
        <i class="el-icon-star-on"></i>
      </div>
      <div v-if=collectionStatus>
        <el-button type="primary" plain class="match_details_but" @click="clickCancelCollect">点击取消收藏</el-button>
        <i class="el-icon-star-on" style="color: gold;"></i>
      </div>
      <br><br>
      <h3>发布者</h3>
      <div id="match_details_right_issuer">
        <img :src="require('../assets/'+releaseInformation.head)" alt="头像">
        <div>{{releaseInformation.issuer}}</div>
      </div>
      <h3>报名时间</h3>
      <p>{{releaseInformation.registrationTime}}</p>
      <h3>比赛时间</h3>
      <p>{{competition.g_sTime}}至{{competition.g_eTime}}</p>
      <h3>主办方</h3>
      <p>{{releaseInformation.sponsor}}</p>
      <h3>竞赛级别</h3>
      <p>{{releaseInformation.level}}</p>
    </div>
  </div>
</template>

<script>
import{addCollect,judgeCollection,showDetail,cancelCollect,count} from "@/api/index"
export default {
  name: "MatchDetails",
  data() {
    return{
      supplement: "无", // 补充信息
      img: 'rotationChart2.png',
      // 比赛信息
      competition:{},
      // 组队信息
      team:[
        {src:'zt.jpg',title:'标题',content:'团队简介---------------------------------------------------------------------------------------------------------------------------------------------------'},
        {src:'zt.jpg',title:'标题',content:'团队简介---------------------------------------------------------------------------------------------------------------------------------------------------'},
        {src:'zt.jpg',title:'标题',content:'团队简介---------------------------------------------------------------------------------------------------------------------------------------------------'}
      ],
      // 发布信息
      releaseInformation:{
        head: "zt.jpg",       // 头像
        issuer: "汪汪大队",   // 发布者
        registrationTime: "2022年4月5日 24:00 至 2022年6月5日12:00", // 报名时间
        sponsor: "教育部",   // 主办方
        level: "国家级"      // 竞赛级别
      },
      collectionStatus:"",//收藏
      collectionCount:""//收藏量
    }
  },
  methods: {
    //收藏比赛
    clickCollection() {
      this.delay(() => {
        // 执行代码
        addCollect().then(res=>{
          if(res.state){
            this.collectionStatus=res.state;
          }
        })
      }, 500)
    },
    //取消收藏
    clickCancelCollect(){
      this.delay(() => {
        // 执行代码
        cancelCollect().then(res=>{
          console.log(3,res)
          this.$data.collectionStatus = !res.success
        })
      }, 500)
    },
    judgmentCollection: function(){
      judgeCollection().then(res=>{
        console.log(res)
        this.$data.collectionStatus = res.state
      })
    },
    showMatchDetail(g_id){
      showDetail(g_id).then(res=>{
        console.log(1,res)
        console.log(2,g_id)
      })
    },
    collectCount(g_id){
      count(g_id).then(res=>{
        this.$data.collectionCount = res
      })
    }
  },
  mounted() {
    this.competition=this.$store.state.match.matchData;
    this.judgmentCollection();
    this.showMatchDetail(this.$route.query.g_id);
    this.collectCount(this.$route.query.g_id)
    // this.showMatchDetail();
  },
}
</script>

<style>
/* 比赛详情页面 */
#matchDetails {
  width: 1440px;
  margin-top: 40px;
}
#matchDetails h3 {
  border-left: 5px solid #2F8FFB;
  padding-left: 10px;
}
#matchDetails p {
  padding-left: 15px;
  width: 90%;
}
/* 顶部的图片 */
.match_details_header {
  display: flex;
}
/* 左下角 */
.match_details_left {
  width: 68%;
  padding-left: 30px;
  float: left;
}
/* 右下角 */
.match_details_right {
  width: 26%;
  float: right;
  padding-right: 20px
}
/* 收藏按钮 */
.match_details_but {
  float: right;
}
.match_details_right i {
  font-size: 30px;
  float: right;
  padding-top: 5px;
}
.match_details_right img {
  width: 36px;
  height: 36px;
  border-radius: 100px;
}
/* 右下角-发布者 */
#match_details_right_issuer {
  height: 36px;
}
/* 右下角-发布者-头像 */
#match_details_right_issuer img {
  float: left;
  margin: 0 15px 0 15px;
}
/* 右下角-发布者-昵称 */
#match_details_right_issuer div {
  float: left;
  line-height: 36px;
}
.count{
  position:absolute;
  top: 620px;
  right: 300px;
}
</style>