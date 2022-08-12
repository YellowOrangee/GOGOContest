<template>
  <div id="matchDetails">
    <!-- 顶部图片 -->
    <div class="match_details_header">
      <el-image
      style="width: 90%; height: 450px; margin: 0 auto;"
      :src="require('../assets/'+competition.img)"
      fit="contain"></el-image>
    </div>
    <br><hr><br>

    <!-- 左下角 -->
    <div class="match_details_left">
      <div style="font: 32px 庞门正道粗书体;text-align: center;">
        {{competition.name}}
        <hr>
      </div>
      <div style="margin-left: 50px;">
        <h3>比赛简介</h3>
        <p>{{competition.introduction}}</p>
        <h3>补充内容</h3>
        <p>{{competition.supplement}}</p>
        <h3>大赛官网</h3>
        <el-link :href="competition.web" target="_blank">{{competition.web}}</el-link>
        <br><br><hr>
        <div style="font: 32px 庞门正道粗书体;">该比赛组队信息</div>
        <hr>
        <!-- 该比赛组队信息 -->
        <el-main>
          <el-row :gutter="0" class="mainContainer" v-for="(item, index) in team" :key="index">
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
      <div v-if=!collectionStatus>
        <el-button type="primary" class="match_details_but" @click="clickCollection">点击收藏</el-button>
        <i class="el-icon-star-on"></i>
      </div>
      <div v-if=collectionStatus>
        <el-button type="primary" plain class="match_details_but" @click="clickCollection">点击取消收藏</el-button>
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
      <p>{{releaseInformation.matchTime}}</p>
      <h3>主办方</h3>
      <p>{{releaseInformation.sponsor}}</p>
      <h3>竞赛级别</h3>
      <p>{{releaseInformation.level}}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "MatchDetails",
  data() {
    return{
      // 比赛信息
      competition:{
        img: 'rotationChart2.png',
        name: "中国国际“互联网+”大学生创新创业大赛",
        introduction: "示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字",
        supplement: "无",
        web: "https://cy.ncss.cn/",
      },
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
        matchTime: "2022年4月5日 24:00 至 2022年6月5日12:00",        // 比赛时间
        sponsor: "教育部",   // 主办方
        level: "国家级"      // 竞赛级别
      },
      collectionStatus: false,//收藏
    }
  },
  methods: {
    clickCollection() {
      this.collectionStatus=!this.collectionStatus;
    },
  }
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
</style>