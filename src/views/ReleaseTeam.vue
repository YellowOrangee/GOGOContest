<template>
  <div id="releaseTeam_main">
    <el-row>
      <!-- 左侧 -->
      <el-form ref="releaseForm" :model="releaseForm" label-width="120px" :rules="rules" class="release_team_form">
        <el-col :span="16" :offset="0" id="releaseTeam_left">
          <!-- 清除信息与预览 -->
          <el-row>
            <el-col :span="5" :offset="0">
              <el-button type="text" @click="clearInformation">点击清除全部信息</el-button>
            </el-col>
            <el-col :span="4" :offset="15">
              <el-button type="text" @click="preview"><i class="el-icon-view">预览</i></el-button>
            </el-col>
          </el-row>

          <!-- 比赛信息 -->
          <el-row class="releaseTeam_left_info">
            <el-row><b>比赛信息</b></el-row>
            <!-- 选择框 -->
            <el-row class="releaseTeam_select">
                <el-form-item label="比赛1：" prop="game1" style="width: 100%;">
                  <el-row>
                    <el-col :span="20">
                      <el-select
                        v-model="releaseForm.game1"
                        clearable
                        filterable
                        remote
                        placeholder="请选择比赛"
                        :remote-method="remoteMethod"
                        :loading="releaseForm.loading"
                        style="width: 100%;">
                        <el-option
                          v-for="item in releaseForm.options"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value">
                        </el-option>
                      </el-select>
                    </el-col>
                    <!-- 加号 -->
                    <el-col :span="3" :offset="0">
                      <el-button type="text" @click="addGameSelect" class="releaseTeam_left_add_icon">
                        <i class="el-icon-circle-plus"></i>
                      </el-button>
                    </el-col>
                  </el-row>
                </el-form-item>

                <el-form-item :label="'比赛'+(index+2)+'：'"
                    v-for="(item, index) in gameItem" :key="index"
                    :prop="item.value"
                    style="width: 100%;">
                  <el-row>
                    <el-col :span="20">
                        <!-- 这里的v-model写releaseForm['value']也可以 -->
                      <el-select
                        v-model="releaseForm[item.value]"
                        clearable
                        filterable
                        remote
                        placeholder="请选择比赛"
                        :remote-method="remoteMethod"
                        :loading="releaseForm.loading"
                        style="width: 100%;">
                        <el-option
                          v-for="(optionItem, optionIndex) in releaseForm.options"
                          :key="optionIndex"
                          :label="optionItem.label"
                          :value="optionItem.value">
                        </el-option>
                      </el-select>
                    </el-col>
                    <!-- 减号 -->
                    <el-col :span="3" :offset="0">
                      <el-button type="text" @click="delGameSelect" class="releaseTeam_left_add_icon">
                        <i class="el-icon-remove"></i>
                      </el-button>
                    </el-col>
                  </el-row>
                </el-form-item>

            </el-row>
            
          </el-row>

          <!-- 组队信息 -->
          <el-row class="releaseTeam_left_info">
            <el-row><b>组队信息</b></el-row>

            <el-row>
                <!-- 上传队伍图片 -->
                <el-form-item label="队伍图片：" prop="teamImg">
                  <el-upload
                      class="avatar-uploader"
                      drag
                      :auto-upload="true"
                      ref="uploadImg"
                      :action="action"
                      :show-file-list="false"
                      :http-request="uploadAction"
                      :on-change="beforeAvatarUpload"
                      style="text-align: left;">
                    <el-image v-if="imageUrl" :src="imageUrl" class="avatar" fit="cover"></el-image>
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    <div class="el-upload__tip" slot="tip">图片大小不能超过 2MB</div>
                  </el-upload>
                </el-form-item>
                <!-- 队伍名称 -->
                <el-row>
                  <el-col :span="21" :offset="0">
                    <el-form-item label="队伍名称：" prop="teamName" style="width: 100%;">
                      <el-input v-model="releaseForm.teamName" placeholder="请输入内容"></el-input>
                    </el-form-item>
                  </el-col>
                  <!-- 队伍名格式正确的符号 -->
                  <el-col :span="2" :offset="0" class="releaseTeam_left_add_icon">
                    <i class="el-icon-success" v-show="teamNameIcon"></i>
                  </el-col>
                </el-row>

                <el-form-item label="队伍简介：" prop="teamIntro">
                  <el-input type="textarea"
                    v-model="releaseForm.teamIntro"
                    placeholder="请输入内容"
                    rows="5"
                    maxlength="200"
                    show-word-limit
                  ></el-input>
                </el-form-item>
                <el-form-item label="组队人数：" style="text-align: left;" prop="memberNum" required>
                  <el-input-number
                    v-model="releaseForm.memberNum"
                    @change="memberNumChange"
                    :min="1"
                    :max="5"
                  ></el-input-number>
                </el-form-item>
                <el-form-item label="联系方式：" prop="teamPhone">
                  <el-input v-model="releaseForm.teamPhone" placeholder="请输入内容"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="info" @click="saveForm">保存到我的队伍</el-button>
                  <el-button type="primary" @click="submitForm">发布该招募信息</el-button>
                </el-form-item>
            </el-row>

          </el-row>
        </el-col>
      </el-form>

      <!-- 右侧 -->
      <el-col :span="8" :offset="0" id="releaseTeam_right">
        <!-- 我的队伍 -->
        <el-row class="releaseTeam_right_info">
          <el-row><b>我的队伍</b></el-row>
          <div class="releaseTeam_team">
            <h4>队名示例</h4>
            <el-image
              style="width: 200px; height: 100px;"
              :src="require('../assets/rotationChart1.png')"
              fit="contain">
            </el-image>
            <el-row>
              <span>团队成员</span>
            </el-row>
            <el-row>
              <el-col :span="6" :offset="0">
                <el-row>
                  <el-image
                    style="width: 40px; height: 40px;border-radius: 100px;"
                    :src="require('../assets/zt.jpg')"
                    fit="cover">
                  </el-image>
                </el-row>
                <el-row class="releaseTeam_team_username">摆烂小天王</el-row>
              </el-col>
            </el-row>
          </div>
        </el-row>
        <!-- 待招募队伍 -->
        <el-row class="releaseTeam_right_info">
          <el-row><b>待招募队伍</b></el-row>
          <div class="releaseTeam_team">
            <h4>队名示例</h4>
            <el-image
              style="width: 200px; height: 100px;"
              :src="require('../assets/rotationChart1.png')"
              fit="contain">
            </el-image>
            <el-row>
              <span>团队成员</span>
            </el-row>
            <el-row>
              <el-col :span="6" :offset="0">
                <el-row>
                  <el-image
                    style="width: 40px; height: 40px;border-radius: 100px;"
                    :src="require('../assets/zt.jpg')"
                    fit="cover">
                  </el-image>
                </el-row>
                <el-row class="releaseTeam_team_username">摆烂小天王</el-row>
              </el-col>
            </el-row>
          </div>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {releaseTeam} from "@/api/index";
import {queryMatchForTeam} from "@/api/index";
export default {
  name: "ReleaseTeam",
  data() {
    const checkTeamName = (rule, value, callback) => {
      if (value.length < 2) {
        this.teamNameIcon = false;  //隐藏格式正确图标
        callback(new Error("队伍名称长一点嘛，别小于2"));
      } else {
        this.teamNameIcon = true;   //显示格式正确图标
        callback();
      }
    };
    return {
      // 比赛选择框（从第二个开始）[ { "value": "game2" }, { "value": "game3" }]
      gameItem:[],
      // 队伍名称格式正确显示的图标
      teamNameIcon:false,
      // 上传图片校验
      checkImg:false,
      // 照片上传的地址
      action:"",
      // 显示照片的url
      imageUrl: '',
      // 表格
      releaseForm:{
        // 比赛信息表
        options: [],  // 根据选择框搜索显示的选项
        game1: {},    // 第一个比赛(第二个是game2:[])
        list: [],     // 从服务器返回后处理好的所有的选项
        loading: false,
        // 队伍信息表
        teamName: '',  //队伍名
        teamIntro: '',//队伍简介（需求）
        memberNum: 1,  //人数
        teamType:' ',    //参赛类型
        teamPublic:false,    //是否公开
        teamPhone: '',  //队长联系方式
        imgFile:'',    //队伍图片
      },
      rules:{
        teamName: [
          { required: true, message: "请输入队伍名称", trigger: "blur" }, //其他规则待添加
          { validator:checkTeamName, trigger: "change" },
          { validator:checkTeamName, trigger: "blur" },
        ],
        teamPhone: [
          { required: true, message: "请输入联系方式", trigger: "change" }, //其他规则待添加
        ],
      },
    }
  },
  methods: {
    // 清除全部信息
    clearInformation(){
      this.$refs["releaseForm"].resetFields()
    },
    // 预览
    preview(){
      this.$refs['releaseForm'].validate((valid) => {
        if (valid) {
          var teamData={}
          // 队伍详情数据写入store
          teamData.t_name=this.releaseForm.teamName;  //队伍名
          teamData.t_captain=this.$store.state.user.uInfo.userName; //用户名
          teamData.t_contact=this.releaseForm.teamPhone;  //联系方式
          teamData.t_count=this.releaseForm.memberNum;  //人数
          teamData.t_ctime=this.releaseForm.date;  //发帖时间（队伍创建时间）
          teamData.t_demand=this.releaseForm.teamIntro; //队伍需求（简介）
          
          this.$store.state.team.teamData=teamData;// 队伍详情数据写入store
          localStorage.setItem(           // 队伍详情数据写入本地储存，防止刷新就没
            "teamData",
            JSON.stringify(teamData)
          );
          this.$message({message:"发布成功",type: "success",});
          this.$router.push({path:'TeamDetails'})
        } else {
          this.$message({message:"必填项不能为空",type: "error",});
          return false;
        }
      });
    },
    // 添加“比赛选择框”
    addGameSelect(){
      if (this.gameItem.length<4) {
        this.gameItem.push({value:'game'+(this.gameItem.length+2)})
      } else{
        this.$message({message:"那么多比赛你打得完吗",type: "warning",});
      }
    },
    // 删除“比赛选择框”
    delGameSelect(){
      this.gameItem.pop() // 直接删除最后一个数据
    },
    // 远程搜索比赛信息
    remoteMethod(query) {
      if (query !== '') {
        this.releaseForm.loading = true;
        setTimeout(() => {
          this.releaseForm.loading = false;
          this.releaseForm.options = this.releaseForm.list.filter(item => {
            return item.label.toLowerCase().indexOf(query.toLowerCase()) > -1;
          });
        }, 200);
      } else {
        this.releaseForm.options = [];
      }
    },
    // http-request可覆盖默认上传行为
    // 上传图片
    uploadAction(data){
      if(this.checkImg){
        this.releaseForm.imgFile=data;
        console.log("上传的图片",this.releaseForm.imgFile);
      }
      else{
        console.log("校验未通过");
      }
    },
    // 上传照片前确认格式
    beforeAvatarUpload(file) {
      console.log("确认格式",file);
      const isIMG = /image\/\w+/.test(file.raw.type)  // file.type === 'image';
      const isLt2M = file.raw.size / 1024 / 1024 < 2;
      if (!isIMG) {
        this.$message.error('只能传图片！');
      } else if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      } else{
        this.imageUrl = URL.createObjectURL(file.raw);
      }
      this.checkImg=isIMG&isLt2M;
      return isIMG && isLt2M;
    },
    // 组队人数改变时调用（目前没用，可删）
    memberNumChange(value) {
      console.log('队伍人数：'+value);
    },
    // 发布该招募信息
    submitForm() {
      this.$refs['releaseForm'].validate((valid) => {
        if (valid) {
          this.releaseForm.game1=JSON.parse(this.releaseForm.game1);  // 将参加比赛的value转换成对象
          this.teamPublic=true;   // 发布类型为公开
          releaseTeam(this.$data.releaseForm).then((res) => {
            console.log("发布该招募信息", res);
            if (res.state==200) {
              this.$message({message:res.msg,type: "success",});
              this.$refs["releaseForm"].resetFields();  // 清空表单
            } else {
              console.log("发布该招募信息错误",res);
              this.$message({message:res.msg,type: "error",});
            }
          });
        } else {
          this.$message({message:"必填项不能为空",type: "error",});
          return false;
        }
      });
    },
    // 保存到我的队伍
    saveForm() {
      this.$refs['releaseForm'].validate((valid) => {
        if (valid) {
          this.teamPublic=false;   // 发布类型为不公开
          this.$message({message:"保存成功",type: "success",});
          this.$refs["releaseForm"].resetFields();  // 清空表单
        } else {
          this.$message({message:"必填项不能为空",type: "error",});
          return false;
        }
      });
    },
  },
  mounted() {
    queryMatchForTeam(' ').then(res=>{
      res=JSON.parse(res);
      console.log("搜索返回",res);
      this.releaseForm.list = res.map(item => {
        return {
          value: `{"name":"${item.g_title}","id":"${item.g_id}"}`,
          label: `${item.g_title}，举办方：${item.g_sponsor}`
        };
      });
    })
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#releaseTeam_main {
  width: 100%;
  background-color: #efeff4;
}
#releaseTeam_main b{
  font-size: 20px;
}
#releaseTeam_main .el-row .el-form-item{
  width: 87.5%;
}
#releaseTeam_left{
  padding: 10px 40px 30px 40px;
}
.releaseTeam_left_info{
  background-color: #fff;
  padding: 30px 0 30px 50px;
  margin: 10px 0 30px 0;
  border-radius: 20px;
}
.releaseTeam_left_info .el-icon-success{
  color: #409eff;
}
#releaseTeam_main b{
  float: left;
  padding-bottom: 10px;
}
/* 比赛选择框 */
.releaseTeam_select{
  margin: 5px 0 5px 0;
}
/* 加号图标 */
.releaseTeam_left_add_icon{
  height: 40px;
  line-height: 40px;
}
.releaseTeam_left_add_icon i{
  font-size: 20px;
}
.el-icon-remove{
  color: #CA1D1D;
}
/* 上传队伍图片 */
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 360px;
  height: 180px;
  line-height: 180px;
  text-align: center;
}
.avatar {
  width: 360px;
  height: 180px;
  display: block;
}
/* 右侧 */
#releaseTeam_right{
  background-color: #fff;
}
.releaseTeam_right_info{
  padding: 20px 0 20px 50px;
}
/* 右侧的队伍的框 */
.releaseTeam_team{
  width: 330px;
  border-radius: 4px;
  border: 1px solid #DCDFE6;
  margin-top: 15px;
  padding: 0 15px 20px 15px;
  text-align: center;
}
/* 团队成员（标题） */
.releaseTeam_team span{
  float: left;
  margin: 15px 0 15px 0;
  font-size: 18px;
  color: #565050;
}
/* 团队成员的名字 */
.releaseTeam_team_username{
  font-size: 14px;
}
</style>