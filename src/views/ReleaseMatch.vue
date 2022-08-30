<template>
  <div id="releaseMatch_body">

    <!-- 清除信息与预览 -->
    <el-row>
      <el-col :span="5" :offset="0">
        <el-button type="text" @click="clearInformation">点击清除全部信息</el-button>
      </el-col>
      <el-col :span="4" :offset="15">
        <el-button type="text" @click="preview"><i class="el-icon-view">预览</i></el-button>
      </el-col>
    </el-row>

    <el-row>
      <el-col id="releaseMatch_main" :span="18" :offset="3">

        <el-row>
          <el-col :offset="2">
            <b id="matchTitle">竞赛信息</b>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="20" :offset="2">

<!--            比赛信息表单-->
            <el-form ref="match" :model="match" label-width="120px" :rules="rules">

              <!-- 比赛名 -->
              <el-row>
                <el-col :span="21" :offset="0">
                  <el-form-item label="比赛名称：" prop="matchName" style="width: 100%;">
                    <el-input v-model="match.matchName" placeholder="请输入内容"></el-input>
                  </el-form-item>
                </el-col>
                <!-- 比赛名格式正确的符号 -->
                <el-col :span="2" :offset="0" class="match_name_icon">
                  <i class="el-icon-success" v-show="matchNameIcon"></i>
                </el-col>
              </el-row>

              <!-- 上传竞赛图片 -->
              <el-form-item label="竞赛图片：" prop="matchImg">
                <el-upload
                    class="avatar-uploader"
                    drag
                    :auto-upload="false"
                    ref="uploadImg"
                    :action="action"
                    :limit=1
                    :on-success="uploadSuccess"
                    :on-error="uploadError"
                    :on-change="beforeAvatarUpload"
                    :on-exceed="imgBeyond"
                    style="text-align: left;">
                  <el-image v-if="imageUrl" :src="imageUrl" class="avatar" fit="cover"></el-image>
                  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                  <div class="el-upload__tip" slot="tip">图片大小不能超过 2MB</div>
                </el-upload>
              </el-form-item>

              <!-- 比赛简介 -->
              <el-form-item label="比赛简介：" prop="matchIntro">
                <el-input type="textarea"
                          v-model="match.matchIntro"
                          placeholder="请输入内容"
                          rows="6"
                          maxlength="500"
                          show-word-limit
                          style="width: 500px;float: left;"
                ></el-input>
              </el-form-item>

              <el-form-item label="报名时间：" prop="signUpTime">
                <el-date-picker
                  v-model="match.signUpTime"
                  type="daterange"
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                  value-format="yyyy-MM-dd"
                  class="needfloat">
                </el-date-picker>
              </el-form-item>
              
              <el-form-item label="比赛时间：" prop="matchTime">
                <el-date-picker
                  v-model="match.matchTime"
                  type="daterange"
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                  value-format="yyyy-MM-dd"
                  class="needfloat">
                </el-date-picker>
              </el-form-item>

              <el-row>
                <el-col :span="20" :offset="0">
                  <el-form-item label="主办方：" prop="organizer">
                    <el-input v-model="match.organizer" placeholder="请输入内容"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-form-item label="竞赛级别：" prop="level">
                <el-select v-model="match.level" clearable placeholder="请选择等级" class="needfloat">
                  <el-option
                    v-for="item in levelOptions"
                    :key="item.value"
                    :label="item.value"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>

              <el-form-item label="竞赛类型：" prop="type">
                <el-select v-model="match.type" clearable placeholder="请选择类型" class="needfloat">
                  <el-option
                    v-for="item in typeOptions"
                    :key="item.value"
                    :label="item.value"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>

              <el-row>
                <el-col :span="20" :offset="0">
                  <el-form-item label="大赛官网：" prop="officialWeb">
                    <el-input v-model="match.officialWeb" placeholder="https://"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="6" :offset="6">
                    <el-button type="info" @click="saveMatch">保存并返回</el-button>
                </el-col>
                <el-col :span="6">
                    <el-button type="primary" @click="submitMatch">发布该竞赛信息</el-button>
                </el-col>
              </el-row>
              
            </el-form>

          </el-col>
        </el-row>
      </el-col>
    </el-row>

  </div>
</template>

<script>
export default {
  name: "ReleaseMatch",
  data() {
    const checkMatchName = (rule, value, callback) => {
      if (value.length < 5) {
        this.matchNameIcon = false;  //隐藏格式正确图标
        callback(new Error("比赛名称长一点嘛，别小于5"));
      } else {
        this.matchNameIcon = true;   //显示格式正确图标
        callback();
      }
    };
    return {
      matchNameIcon: false, // 是否显示比赛名称后面的图标
      action:"",  // 照片上传的地址
      imageUrl: '', // 照片的url
      match: {
        matchName: '',  // 比赛名称
        matchIntro: '',  // 比赛简介
        signUpTime: [], // 报名时间[开始时间,结束时间]
        matchTime: [],  // 比赛时间[开始时间,结束时间]
        organizer: '', // 主办方
        level: '',  // 竞赛级别
        type: '',  // 竞赛类型
        officialWeb: '',  //大赛官网
      },
      // 竞赛级别选项
      levelOptions: [
        {value: '校级',},
        {value: '市级',},
        {value: '省级',},
        {value: '全国性',},
        {value: '全球性',}
      ],
      // 竞赛类型选项
      typeOptions: [
        {value: '工科',},
        {value: '理科',},
        {value: '文科',},
        {value: '商科',},
        {value: '其他',}
      ],
      rules:{
        matchName: [
          { required: true, message: "请输入比赛名称", trigger: "blur" }, //其他规则待添加
          { validator:checkMatchName, trigger: "change" },
          { validator:checkMatchName, trigger: "blur" },
        ],
        signUpTime: [
          { required: true, message: "不能没有时间", trigger: "blur" }, //其他规则待添加
        ],
        matchTime: [
          { required: true, message: "不能没有时间", trigger: "blur" }, //其他规则待添加
        ],
        organizer: [
          { required: true, message: "报出你主办方的名字", trigger: "blur" }, //其他规则待添加
        ],
        level: [
          { required: true, message: "选个等级，便于查找", trigger: "blur" }, //其他规则待添加
        ],
        type: [
          { required: true, message: "选个类型，便于查找", trigger: "blur" }, //其他规则待添加
        ],
        officialWeb: [
          { required: true, message: "请输入比赛官网", trigger: "blur" }, //其他规则待添加
          { required: true,type: 'url', message: "请输入正确的url，格式例子：https://www.baidu.com", trigger: "change" },
          { type: 'url', message: "请输入正确的url，格式例子：https://www.baidu.com", trigger: "blur" },
        ]
      }
    }
  },
  methods: {
    // 清除全部信息
    clearInformation(){
      this.$refs["match"].resetFields();
    },
    // 预览
    preview(){
      this.$refs['match'].validate((valid) => {
        if (valid) {
          var matchData={}
            /* 比赛数据写入store */
          matchData.g_title=this.match.matchName;  //队伍名
          matchData.g_body=this.match.matchIntro;  //比赛简介
          // matchData.xxxxx=this.match.matchTime[0]; // 报名开始时间
          // matchData.xxxxx=this.match.matchTime[1]; // 报名结束时间
          matchData.g_sTime=this.match.matchTime[0];  //大赛开始时间
          matchData.g_eTime=this.match.matchTime[1]; //大赛结束时间
          // matchData.xxxxx=this.match.organizer; //主办方
          // matchData.xxxxx=this.match.level; //竞赛级别
          matchData.g_type=this.match.type; //大赛类型
          matchData.g_link=this.match.officialWeb;  //大赛官网
          // matchData.g_id=this.$store.state.user.uInfo.userId; //发布者id（用户似乎没有id吧）

          this.$store.state.match.matchData=matchData;// 比赛数据写入store
          localStorage.setItem(           // 比赛数据写入本地储存，防止刷新就没
            "matchData",
            JSON.stringify(matchData)
          );
          this.$router.push({path:'./matchDetails'})
        } else {
          this.$message({message:"必填项不能为空",type: "error",});
          return false;
        }
      });
    },
    // http-request可覆盖默认上传行为，使用方法百度elementui http-request
    // 上传照片成功
    uploadSuccess(res, file) {
      console.log('上传成功',file);
      this.imageUrl = URL.createObjectURL(file.raw);
      this.$message.error('上传成功');
    },
    // 上传照片失败
    uploadError(res,file){
      console.log('上传失败',file);
      this.imageUrl = URL.createObjectURL(file.raw);
      this.$message.error('上传失败');
    },
    // 上传照片前确认格式
    beforeAvatarUpload(file) {
      console.log(file);
      const isIMG = /image\/\w+/.test(file.raw.type)  // file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isIMG) {
        this.$message.error('只能传图片！');
      } else if (!isLt2M) {
        this.$message.error('上传图片大小不能超过 2MB!');
      } else{
        this.imageUrl = URL.createObjectURL(file.raw);
      }
      return isIMG && isLt2M;
    },
    // 上传图片数量超出
    imgBeyond(files, fileList){
      console.log('files',files);
      console.log('fileList',fileList);
    },
    saveMatch(){
      this.$refs['match'].validate((valid) => {
        if (valid) {
          this.$refs.uploadImg.submit();  // 上传图片
          this.$message({message:"保存成功",type: "success",});
          this.$refs["match"].resetFields();  // 清空表单
        } else {
          this.$message({message:"必填项不能为空",type: "error",});
          return false;
        }
      });
    },
    submitMatch(){
      this.$refs['match'].validate((valid) => {
        if (valid) {
          this.$refs.uploadImg.submit();  // 上传图片
          this.$message({message:"发布成功",type: "success",});
          this.$refs["match"].resetFields();  // 清空表单
        } else {
          this.$message({message:"必填项不能为空",type: "error",});
          return false;
        }
      });
    },
  },
}
</script>

<style scoped>
/*发布比赛页面*/
#releaseMatch_body{
  width: 100%;
  background-color: #efeff4;
  padding: 20px 0 90px 0;
}
/*竞赛信息（白色的那块）*/
#releaseMatch_main{
  margin-top: 20px;
  padding: 50px 0 50px 0;
  background-color: #fff;
  border-radius: 20px;
}
/*竞赛信息（那四个大字）*/
#matchTitle{
  margin-bottom: 20px;
  float: left;
}
/*比赛名称后面的图标*/
.match_name_icon{
  height: 40px;
  line-height: 40px;
}
.match_name_icon i{
  font-size: 20px;
  color: #2F8FFB;
}
/* 上传竞赛图片 */
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
/* 需要向左float的一些表单元素 */
.needfloat{
  float: left;
}
</style>