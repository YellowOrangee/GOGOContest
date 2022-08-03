<template>
  <div id="messagePage">
    <el-row class="messageBox" v-for="information in informations" :key="information.lable">
      <div>
        <el-tag :type="information.type" effect="plain" class="messageTag">
          {{information.label}}
        </el-tag>
        <span style="margin-left: 70px;">{{information.message}}</span>
        <span style="float: right;margin-right: 70px;">{{information.date}}</span>
      </div>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "MessageView",
  data() {
      return {
          // 比赛消息-warning-黄色，组队消息-danger-红色，系统消息-success-绿色
        informations: [
          { type: 'warning', label: '比赛消息', message: '你关注的比赛信息有了更新', date:'2022/7/31' },
          { type: 'danger', label: '组队消息', message: 'xxx申请加入你的队伍', date:'2022/7/31' },
          { type: 'success', label: '系统消息', message: '示例信息示例信息示例信息', date:'2022/7/31' },
          { type: 'success', label: '系统消息', message: '示例信息示例信息示例信息', date:'2022/7/31' },
          { type: 'success', label: '系统消息', message: '示例信息示例信息示例信息', date:'2022/7/31' },
          { type: 'warning', label: '比赛消息', message: '你关注的比赛信息有了更新', date:'2022/7/31' },
          { type: 'danger', label: '组队消息', message: 'xxx申请加入你的队伍', date:'2022/7/31' },
          { type: 'danger', label: '组队消息', message: 'xxx申请加入你的队伍', date:'2022/7/31' },
          { type: 'warning', label: '比赛消息', message: '你关注的比赛信息有了更新', date:'2022/7/31' },
          { type: 'danger', label: '组队消息', message: 'xxx申请加入你的队伍', date:'2022/7/31' },
          { type: 'danger', label: '组队消息', message: 'xxx申请加入你的队伍', date:'2022/7/31' },
        ]
      }
    }
}
</script>

<style>
#messagePage{
  width: 100%;
  background-color: #f8f9f9;
  padding: 0 40px 50px 40px;
}
.messageBox{
  background-color: #fff;
  margin-top: 40px;
  height: 80px;
  border-radius: 15px;
  line-height: 75px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}
.messageTag{
  margin-left: 35px;
}
</style>