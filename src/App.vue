<template>
  <div id="app">
    <el-container>
      <el-header>
        <el-row>
          <el-col :span="3" :offset="0"
            ><img src="./assets/logo.png" class="logo"
          /></el-col>
          <el-col :span="8" :offset="11">
            <div class="route">
              <router-link to="/">组队论坛</router-link>
              <router-link to="/match">比赛信息</router-link>
              <router-link to="/individual">个人主页</router-link>
            </div>
          </el-col>
        </el-row>
      </el-header>
      <el-container>
        <router-view></router-view>
      </el-container>
    </el-container>
  </div>
</template>

<style>
#app {
  margin-top: -10px;
  margin-left: -8px;
}
.el-header {
  width: 1440px;
  height: 92px;
  line-height: 20px;
  background-color: rgba(255, 255, 255, 100);
  text-align: center;
  box-shadow: 0px 3px 6px 0px rgba(0, 0, 0, 0.4);
  border: 1px solid rgba(187, 187, 187, 100);
}
.el-col .route {
  width: 525px;
  height: 60px;
  line-height: 20px;
  text-align: center;
  display: flex;
  justify-content: space-around;
  align-items: center;
}
.route a {
  text-decoration: none;
  left: 1077px;
  color: rgba(16, 16, 16, 100);
  font-size: 16px;
  text-align: left;
  font-family: SourceHanSansSC-regular;
}
a.router-link-active.router-link-exact-active {
  font-size: 20px;
  padding-bottom: 12px;
  border-bottom: 2px #3894ff solid;
  font-weight: bold;
}
.el-col .logo {
  width: 188px;
  height: 43px;
  margin-top: 10px;
}
</style>
