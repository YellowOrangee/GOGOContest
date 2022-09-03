<template>
  <div class="mainContent">
    <div class="tContainer">
      <span class="title">隐私设置</span>
      <div class="box">
        <span>开启后个人主页显示发布内容</span>
        <el-switch
          v-model="value1"
          active-color="#13ce66"
          inactive-color="#ff4949">
        </el-switch>
      </div>
      <div class="box">
        <span>开启后个人主页显示发布内容</span>
        <el-switch
          v-model="value2"
          active-color="#13ce66"
          inactive-color="#ff4949">
        </el-switch>
      </div>
    </div>
    <div class="bottomContainer">
      <span class="title">外观设置</span>
      <div class="box">
        <span>切换语言：</span>
        <el-switch
          v-model="value3"
          active-text="中文"
          inactive-text="英文">
        </el-switch>
      </div>
      <div class="box">
        <span>暗色模式</span>
        <el-switch
          v-model="value4">
        </el-switch>
      </div>
    </div>
    <div class="bottom">
      <el-button type="primary" plain>切换账号</el-button>
      <el-button type="success" plain>退出登录</el-button>
      <el-button type="info" plain @click="quit">注销账号</el-button>
    </div>
  </div>
</template>

<script>
import router from '@/router';
export default {
    name:"SetupView",
    data() {
      return {
        value1:true,
        value2:false,
        value3:true,
        value4:false
      }
    },
    methods:{
      quit(){
        router.push({path:'/login'});
        var localStorage = window.localStorage;
        localStorage.clear();
        location.reload([true])
      }
    }
}
</script>

<style>
.mainContent{
  background: #EFEFF4;
  width: 1440px;
  height: 982px;
}
.tContainer{
  position: relative;
  top: 63px;
  left:43px;
  width: 1356px;
  height: 245px;
  line-height: 20px;
  border-radius: 20px 20px 20px 20px;
  background-color: rgba(255, 255, 255, 100);
  color: rgba(16, 16, 16, 100);
  font-size: 14px;
  font-family: Roboto;
}
.title{
  position: relative;
  top: 45px;
  left: 45px;
  font-family: 庞门正道粗书体;
  width: 112px;
  height: 43px;
  color: rgba(16, 16, 16, 100);
  font-size: 28px;
  text-align: left;

}
.bottomContainer{
  position: relative;
  top: 171px;
  left: 43px;
  width: 1356px;
  height: 235px;
  line-height: 20px;
  border-radius: 20px 20px 20px 20px;
  background-color: rgba(255, 255, 255, 100);
  color: rgba(16, 16, 16, 100);
  font-size: 14px;
  font-family: Roboto;
}
.box{
  position: relative;
  top: 80px;
  left: 101px;
}
.bottom{
  width: 697px;
  display: flex;
  justify-content: space-around;
  position: relative;
  top: 400px;
  left: 190px;
}
</style>