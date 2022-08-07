<template>
  <div class="main">
    <div class="topContainer">
      <div class="topContainer-left">
        <img :src="require('../assets/'+user.picture)" alt="" class="portrait">
        <div class="information">
          <div><span>账号: </span><span>{{user.account}}</span></div>
          <div><span>用户昵称：</span><span>{{user.id}}</span></div>
          <div class="introduce"><p class="a">个人介绍:</p><p class="b">{{user.introduce}}</p></div>
        </div>
      </div>
      <div class="topContainer-right">
        <el-button type="primary" icon="el-icon-edit" circle class="button"></el-button>
      </div>
    </div>

    <div class="bottonContainer">
      <div class="bottonContainer-main">
        <el-tabs v-model="activeName" @tab-click="handleClick" stretch lazy>
          <el-tab-pane label="基本信息" name="first">
            <el-row :gutter="20">
              <el-col :span="12" :offset="0">姓名：{{user.name}}</el-col>
              <el-col :span="12" :offset="0">电话号码：{{user.phone}}</el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="12" :offset="0">性别：{{user.sex}}</el-col>
              <el-col :span="12" :offset="0">QQ号：{{user.QQ}}</el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="12" :offset="0">地区：{{user.address}}</el-col>
              <el-col :span="12" :offset="0">微信号：{{user.VX}}</el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="12" :offset="0">学校名称：{{user.studies.school}}</el-col>
              <el-col :span="12" :offset="0">电子邮箱：{{user.mail}}</el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="12" :offset="0">学院名称：{{user.studies.college}}</el-col>
              <el-col :span="12" :offset="0">个人爱好：{{user.hobby}}</el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="12" :offset="0">专业名称：{{user.studies.subject}}</el-col>
              <el-col :span="12" :offset="0">其他：xxxx</el-col>
            </el-row>
          </el-tab-pane>
          <el-tab-pane label="组队信息" name="second">
            <div class="container">
              <div class="listContainer">
                <div class="item">
                  <div class="tag"><el-tag type="warning" >比赛信息</el-tag></div>
                  <div class="title">标题</div>
                  <img src="../assets/zt.jpg" alt="" class="matchImg">
                  <div class="content">
                    <p>简介---------------------------------------------------------------------------------------------------------------------------------------------------</p>
                  </div>
                </div> 
                <div class="item">
                  <div class="tag"><el-tag type="danger" >组队信息</el-tag></div>
                  <div class="title">标题</div>
                  <img src="../assets/zt.jpg" alt="" class="matchImg">
                  <div class="content">
                    <p>简介---------------------------------------------------------------------------------------------------------------------------------------------------</p>
                  </div>
                </div> 
                <div class="item">
                  <div class="tag"><el-tag type="warning" >比赛信息</el-tag></div>
                  <div class="title">标题</div>
                  <img src="../assets/zt.jpg" alt="" class="matchImg">
                  <div class="content">
                    <p>简介---------------------------------------------------------------------------------------------------------------------------------------------------</p>
                  </div>
                </div> 
              </div>
              <el-pagination
                layout="prev, pager, next"
                :total="50" >
              </el-pagination>
            </div>
          </el-tab-pane>
          <el-tab-pane label="消息通知" name="third">
            <div class="container">
              <div class="listContainer">
                <div class="item">
                  <div class="tag"><el-tag type="warning" >比赛信息</el-tag></div>
                  <div class="title">标题</div>
                  <img src="../assets/zt.jpg" alt="" class="matchImg">
                  <div class="content">
                    <p>简介---------------------------------------------------------------------------------------------------------------------------------------------------</p>
                  </div>
                </div> 
                <div class="item">
                  <div class="tag"><el-tag type="danger" >组队信息</el-tag></div>
                  <div class="title">标题</div>
                  <img src="../assets/zt.jpg" alt="" class="matchImg">
                  <div class="content">
                    <p>简介---------------------------------------------------------------------------------------------------------------------------------------------------</p>
                  </div>
                </div> 
                <div class="item">
                  <div class="tag"><el-tag type="danger" >组队信息</el-tag></div>
                  <div class="title">标题</div>
                  <img src="../assets/zt.jpg" alt="" class="matchImg">
                  <div class="content">
                    <p>简介---------------------------------------------------------------------------------------------------------------------------------------------------</p>
                  </div>
                </div> 
              </div>
              <el-pagination
                layout="prev, pager, next"
                :total="50" >
              </el-pagination>
            </div>
          </el-tab-pane>
      </el-tabs>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'IndividualView',
  data() {
    return {
      user:{
        picture:'zt.jpg',
        account:'23113241',
        id:'汪汪大队',
        name:'XXX',
        introduce:'这是一段自我介绍，但我编不出来因该写啥了，凑活看看吧，有个形式大概能感受出来的吧（应该）',
        phone:'15222866xxx',
        sex:'女',
        QQ:'1825790xxx',
        VX:'未填',
        address:'天津市西青区',
        hobby:'羽毛球 摄影 舞蹈',
        mail:"sunyu@tiangong.edu.cn",
        studies:{
          school:'天津xx大学',
          subject:'软件工程专业',
          college:'软件学院'
        }
      },
      activeName:'first'
    }
  },
  methods: {
      handleClick(tab, event) {
        console.log(tab, event);
      }
    }
}
</script>

<style>
.main{
  width: 1440px;
  height: 800px;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 69px;
}
.topContainer{
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 1279px;
  height: 225px;
  line-height: 20px;
  border-radius: 15px 15px 15px 15px;
  background-color: rgba(255, 255, 255, 100);
  color: rgba(16, 16, 16, 100);
  font-size: 14px;
  text-align: center;
  box-shadow: 5px 7px 9px 3px rgba(0, 0, 0, 0.22);
  font-family: Roboto;
}
.topContainer .portrait{
  width: 156px;
  height: 156px;
  border-radius: 3px;
}
.topContainer-left{
  padding-left: 67px;
  display: flex;
  justify-content: space-around;
  align-items: center;
}
.topContainer .information{
  height: 180px;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  text-align: left;
  padding-left: 30px;
}
.information span{
  width: 173px;
  height: 30px;
  color: rgba(113, 107, 107, 100);
  font-size: 20px;
  text-align: center;
  font-family: SourceHanSansSC-bold;
}
.introduce{
  display: flex;
  align-items: flex-start;
  width: 718px;
  height: 120px;
}
.introduce .a{
  width: 100px;
  height: 30px;
  color: rgba(113, 107, 107, 100);
  font-size: 20px;
  text-align: center;
  font-family: SourceHanSansSC-bold;
}
.introduce .b{
  width: 718px;
  height: 60px;
  color: rgba(113, 107, 107, 100);
  font-size: 20px;
  text-align: left;
  font-family: SourceHanSansSC-regular;
}
.topContainer-right{
  padding-right:37px;
  position: relative;
  top: -50px;
}
.bottonContainer{
  position: relative;
  top: 67px;
  width: 1279px;
  height: 460px;
  line-height: 20px;
  border-radius: 15px 15px 15px 15px;
  background-color: rgba(255, 255, 255, 100);
  text-align: center;
  box-shadow: 5px 7px 9px 3px rgba(0, 0, 0, 0.22);
  display: flex;
  flex-direction: column;
  align-items: center;
}
.bottonContainer .bottonContainer-main{
  width: 1096px;
}
.bottonContainer-main .el-row{
  padding: 15px 0;
}
.el-row .el-col{
  color: rgba(16, 16, 16, 100);
  font-size: 20px;
  text-align: center;
  font-family: SourceHanSansSC-bold;
}
.container{
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-around;
  width: 1096px;
  height: 400px;
  line-height: 20px;
  border-radius: 0px 0px 30px 30px;
  background-color: rgba(239, 239, 244, 100);
  color: rgba(16, 16, 16, 100);
  font-size: 14px;
  text-align: center;
  font-family: Roboto;
}
.listContainer{
  display: flex;
  align-items: center;
  justify-content: space-around;
  width: 1096px;
  height: 400px;
  line-height: 20px;
  border-radius: 0px 0px 30px 30px;
  background-color: rgba(239, 239, 244, 100);
  color: rgba(16, 16, 16, 100);
  font-size: 14px;
  text-align: center;
  font-family: Roboto;
}
.container .item{
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 341px;
  height: 344px;
  line-height: 20px;
  border-radius: 4px 4px 4px 4px;
  background-color: rgba(255, 255, 255, 100);
  text-align: center;
}
.item .tag{
  position: relative;
  top: 12px;
  right: -124px;
}
.item .matchImg{
  width: 175px;
  height: 126px;
  padding: 10px 0;
}
.item .content{
  width: 252px;
  height: 64px;
  color: rgba(144, 147, 153, 100);
  font-size: 14px;
  text-align: left;
  font-family: Helvetica-regular;
}
</style>
