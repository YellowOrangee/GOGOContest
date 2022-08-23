<template>
  <div>
    <!-- 顶部的轮播图 -->
    <el-carousel :interval="4000" type="card" height="300px" autoplay>
      <el-carousel-item v-for="item in imgs" :key="item">
        <img :src="require('../assets/'+item)" alt="" style="width: 720px;height: 300px;">
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
        <el-row :gutter="0">
          <el-col :span="4" :offset="19">
            <i class="el-icon-plus addMatch" @click="toReleaseCompetition">我要发布竞赛</i>
          </el-col>
        </el-row>
        <el-row :gutter="0" class="mainContainer" v-for="(item, index) in list" :key="index">
          <el-col :span="22" :offset="1">
            <el-card class="box-card">
            <div id="matchList" class="box-card-content" @click="toDetail(index)">
              <img :src="require('../assets/'+src)" alt="">
              <div class="main1">
                <span>{{item.g_title}}</span>
                <div class="content">
                  {{item.g_body}}
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
              :page-size="pageSize"
              background
              @current-change="toPrePage">
            </el-pagination>
          </el-col>
        </el-row>
      </el-main>
    </el-container>

  </div>
</template>

<script>
import { showMatch } from '@/api'
import router from '@/router'

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
        {id:'1',src:'zt.jpg',title:'标题',content:'简介---------------------------------------------------------------------------------------------------------------------------------------------------'},
        {id:'2',src:'zt.jpg',title:'标题',content:'简介---------------------------------------------------------------------------------------------------------------------------------------------------'},
        {id:'3',src:'zt.jpg',title:'标题',content:'简介---------------------------------------------------------------------------------------------------------------------------------------------------'}
      ],
      src:'zt.jpg',
      total:0,    // 总条目数
      pageSize:4, // 每页的数量
      list:[],    // 比赛信息
    }
  },
  methods:{
    toDetail(id){
      //传递参数
      // router.push({path:'/matchDetails',query:{id:''}})
      console.log("比赛序号",id);
      // console.log(this.list[id]);
      this.$store.state.match.matchData=this.list[id]; // 比赛详情数据写入store
      localStorage.setItem(           // 比赛详情数据写入本地储存，防止刷新就没
        "matchData",
        JSON.stringify(this.list[id])
      );
      router.push({path:'/matchDetails'})
    },
    toReleaseCompetition(){
      console.log("1")
    },
    // 更新比赛信息
    upMatchData(num){
      this.delay(()=>{
        showMatch(num).then((res)=>{
          res=JSON.parse(res) //服务器返回的是字符串，需要转成对象
          console.log("所有比赛的信息：",res);
          this.total=res.total; //总条目数
          this.pageSize=res.pageSize; //每页的数量
          this.list=res.list; //比赛信息
        })
      },500)
    },
    //加载当前页的比赛信息
    toPrePage(nowPage){
      this.upMatchData(nowPage);
    },
  },
  mounted() {
    this.upMatchData(1); // 加载第一页的队伍列表
  },
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
.addMatch{
  color: rgba(47, 143, 251, 100);
  font-size: 16px;
  text-align: left;
  font-family: SourceHanSansSC-regular;
  cursor: pointer;
}
#matchList{
  cursor: pointer;
}
</style>