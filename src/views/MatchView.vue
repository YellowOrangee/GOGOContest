<template>
  <div>
    <!-- 顶部的轮播图 -->
    <el-carousel :interval="4000" type="card" height="300px" autoplay>
      <el-carousel-item v-for="item in imgs" :key="item">
        <img
          :src="require('../assets/' + item)"
          alt=""
          style="width: 720px; height: 300px"
        />
      </el-carousel-item>
    </el-carousel>

    <el-container>
      <!-- 左侧 -->
      <el-aside width="509px">
        <el-row class="search">
          <el-col :span="16" :offset="1">
            <div>
              <el-input
                placeholder="请输入内容"
                v-model="content"
                clearable
              ></el-input>
            </div>
          </el-col>
          <el-button type="primary" icon="el-icon-search" @click="search()">搜索</el-button>
        </el-row>

        <!-- 比赛 -->
        <el-row>
          <el-col :span="7" :offset="2" class="title1">配置比赛信息</el-col>
          <el-button type="primary" icon="el-icon-search" @click="search1()" size="small"></el-button>
        </el-row>

        <el-row>
          <el-col :span="5" :offset="2" class="time1">报名时间：</el-col>
          <el-col :span="3">
            <div class="datePicker">
              <el-date-picker @change="chooseTime"
                v-model="value"
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
              <el-dropdown @command="handleCommand1" size="small" split-button type="primary">
                <span class="el-dropdown-link">
                  {{ type ? type : "比赛类型" }}
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item command="全部">全部</el-dropdown-item>
                  <el-dropdown-item command="工科">工科</el-dropdown-item>
                  <el-dropdown-item command="理科">理科</el-dropdown-item>
                  <el-dropdown-item command="文科" >文科</el-dropdown-item>
                  <el-dropdown-item command="商科" >商科</el-dropdown-item>
                  <el-dropdown-item command="其他" >其他</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </div>
          </el-col>

          <el-col :span="6" :offset="0">
            <div>
              <el-dropdown @command="handleCommand2" size="small" split-button type="primary">
                <span class="el-dropdown-link">
                  {{ grade?grade:"比赛级别"}}
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item command="全部">全部</el-dropdown-item>
                  <el-dropdown-item command="校级">校级</el-dropdown-item>
                  <el-dropdown-item command="市级">市级</el-dropdown-item>
                  <el-dropdown-item command="省级" >省级</el-dropdown-item>
                  <el-dropdown-item command="全国" >全国</el-dropdown-item>
                  <el-dropdown-item command="全球" >全球</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </div>
          </el-col>
        </el-row>
      </el-aside>

      <!-- 右侧 -->
      <el-main>
        <el-row>
          <el-col :span="4" :offset="19">
            <el-button type="text" class="addMatch">
              <i class="el-icon-plus" @click="toReleaseCompetition"
                >我要发布竞赛</i
              >
            </el-button>
          </el-col>
        </el-row>
        <el-row
          class="mainContainer"
          v-for="(item, index) in list"
          :key="list[index].g_id"
        >
          <el-col :span="22" :offset="1">
            <el-card class="box-card">
              <div
                id="matchList"
                class="box-card-content"
                @click="toDetail(index, list[index].g_id)"
              >
                <img :src="item.g_image" alt="" />
                <div class="main1">
                  <span>{{ item.g_title }}</span>
                  <div class="content">
                    {{ item.g_body }}
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
              @current-change="toPrePage"
            >
            </el-pagination>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import { showMatch} from "@/api";
import router from "@/router";

export default {
  name: "MatchView",
  data() {
    return {
      imgs: ["rotationChart1.png", "rotationChart2.png", "rotationChart3.png"],
      content: "",
      type: "",
      grade: "",
      value:'',
      total: 0, // 总条目数
      pageSize: 4, // 每页的数量
      list: [], // 比赛信息
      time:{
        sTime:'',
        eTime:''
      }
    };
  },
  methods: {
    //将日期选择器的时间更改格式并保存到data
    chooseTime(){
      this.$data.time.sTime=this.dayjs(this.$data.value[0]).format("YYYY-MM-DD");
      this.$data.time.eTime=this.dayjs(this.$data.value[1]).format("YYYY-MM-DD");
      console.log(this.$data.time);
    },
    //竞赛类型菜单
    handleCommand1(command) {
      this.$data.type = command;
      console.log(this.$data.type);
    },
    //竞赛级别菜单
    handleCommand2(command) {
      this.$data.grade = command;
      console.log(this.$data.grade);
    },
    //按类型级别时间搜索
    async search1(){
      const {sTime,eTime} = this.$data.time;
      const {type,grade} = this;
      // searchByCondition({sTime,eTime,type,grade});
      this.$store.dispatch("getMatchListByCondition",{sTime,eTime,type,grade});
      this.$data.list = this.$store.state.match.list;
    },
    //搜索
    async search(){
      const {content}=this;
      this.$store.dispatch("getMatchListByPage",content);
      this.$data.list = this.$store.state.match.list;
      console.log("输入内容",content);
    },
    //跳转详情页
    toDetail(id, g_id) {
      //传递参数
      console.log("比赛id", g_id, "比赛序号", id);
      this.$store.state.match.matchData = this.list[id]; // 比赛详情数据写入store
      localStorage.setItem(
        // 比赛详情数据写入本地储存，防止刷新就没
        "matchData",
        JSON.stringify(this.list[id])
      );
      router.push({ path: "/matchDetails", query: { g_id } });
    },
    toReleaseCompetition() {
      router.push({ path: "/releaseMatch" });
    },
    // 更新比赛信息
    upMatchData(num) {
      this.delay(() => {
        showMatch(num).then((res) => {
          res = JSON.parse(res); //服务器返回的是字符串，需要转成对象
          console.log("所有比赛的信息：", res);
          this.total = res.total; //总条目数
          this.pageSize = res.pageSize; //每页的数量
          this.$data.list = res.list; //比赛信息
        });
      }, 500);
    },
    //加载当前页的比赛信息
    toPrePage(nowPage) {
      this.upMatchData(nowPage);
    },
  },
  mounted() {

    this.upMatchData(1); // 加载第一页的队伍列表
  },
};
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

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
.addMatch i {
  font-size: 16px;
  text-align: left;
  font-family: SourceHanSansSC-regular;
}
#matchList {
  cursor: pointer;
}
</style>