<template>
  <div>
    <!-- 顶部的轮播图 -->
    <el-carousel :interval="4000" type="card" height="300px" autoplay>
      <el-carousel-item v-for="item in imgs" :key="item">
        <img :src="require('../assets/'+item)" alt="">
      </el-carousel-item>
    </el-carousel>

    <el-container>
      <!-- 左侧 -->
      <el-aside width="509px">
        <el-row class="search">
          <el-col :span="16" :offset="1">
            <div>
              <el-input placeholder="请输入内容" v-model="content" clearable></el-input>
            </div>
          </el-col>
          <el-button type="primary" icon="el-icon-search">搜索</el-button>
        </el-row>

        <!-- 比赛 -->
        <el-row :gutter="2">
          <el-col :span="7" :offset="2" class="title1">配置比赛信息</el-col>
        </el-row>

        <el-row :gutter="0">
          <el-col :span="5" :offset="2" class="time1">报名时间：</el-col>
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

        <el-row :gutter="0" justify="space-around" class="menu">
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
        <el-row :gutter="2">
          <el-col :span="7" :offset="2" class="title2">配置组队信息</el-col>
        </el-row>

        <el-row :gutter="0" class="information">
          <el-col :span="5" :offset="2" class="time1">报名时间：</el-col>
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

        <el-row :gutter="0" justify="space-around" class="menu">
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
      <el-main>
        <el-row :gutter="0" class="mainContainer" v-for="(item, index) in match" :key="index">
          <el-col :span="22" :offset="1">
            <el-card class="box-card" body-style="{
            width: '807px';
            height: '154px';
            line-height: '20px';
            border-radius: '4px 4px 4px 4px';
            background-color: 'rgba(255, 255, 255, 100)';
            text-align: 'center';
            border: '1px solid rgba(25, 137, 250, 100)';
            padding:'20px'
          }">
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
    </el-container>

  </div>
</template>

<script>

export default {
  name: 'MatchView',
  data(){
    return{
      imgs:["rotationChart1.png",
            "rotationChart2.png",
            "rotationChart3.png"],
      content:"",
      value1: "",
      value2: "",
      match:[
        {src:'zt.jpg',title:'标题',content:'简介---------------------------------------------------------------------------------------------------------------------------------------------------'},
        {src:'zt.jpg',title:'标题',content:'简介---------------------------------------------------------------------------------------------------------------------------------------------------'},
        {src:'zt.jpg',title:'标题',content:'简介---------------------------------------------------------------------------------------------------------------------------------------------------'}
      ]
    }
  }
}
</script>
<style>
/* 轮播图 */
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }
  
  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }
  
  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }

/* 代码复用 */
  .el-container{
  height: 900px;
}
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
  float: left;
}
.box-card-content .main1{
  padding: 0 20px ;
}
.content{
  padding-top: 14px;
  width: 573px;
  height: 64px;
  color: rgba(144, 147, 153, 100);
  font-size: 14px;
  text-align: left;
  font-family: Helvetica-regular;
  float: left;
}
</style>