<template>
  <div class="main">
    <div class="topContainer">
      <div class="topContainer-left">
        <img src="../assets/zt.jpg" alt="" class="portrait">
        <div class="information">
          <div><span>账号: </span><span>23113241</span></div>
          <div><span>用户昵称：</span><span>汪汪大队</span></div>
          <div><span>个人介绍：</span><p class="introduce">这是一段自我介绍，但我编不出来因该写啥了，凑活看看吧，有个形式大概能感受出来的吧（应该）</p></div>
        </div>
      </div>
      <div class="topContainer-right">
        <el-button type="primary" icon="el-icon-edit" circle class="button"></el-button>
      </div>
    </div>

    <div class="buttonContainer">

    </div>
  </div>
</template>

<script>

export default {
  name: 'IndividualView',
}
</script>

<style>
.main{
  width: 95%;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 69px;
}
.topContainer{
  display: flex;
  justify-content: space-around;
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
.topContainer .information{
  height: 156px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  text-align: left;
}
.information span{
  width: 173px;
  height: 30px;
  color: rgba(113, 107, 107, 100);
  font-size: 20px;
  text-align: center;
  font-family: SourceHanSansSC-bold;
  margin: 10px 0;
}
.introduce{
  width: 718px;
  height: 70px;
  color: rgba(113, 107, 107, 100);
  font-size: 20px;
  text-align: left;
  font-family: SourceHanSansSC-regular;
}
.topContainer-left{
  width: 80%;
  display: flex;
  justify-content: space-around;
  align-items: center;
}
</style>
