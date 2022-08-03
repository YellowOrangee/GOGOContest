<template>
  <div class="login">
    <div class="loginBox">
      <!-- 登录 -->
      <el-form ref="signInForm" label-width="126px" :model="signInForm">
        <div id="signIn" v-if="signInPage">
          <el-row>
            <el-col :span="14" :offset="5">
              <img src="../assets/logo.png" />
            </el-col>
          </el-row>
          <br /><br />
          <el-row>
            <el-col :span="16" :offset="4">
              <el-form-item
                label="用户名："
                prop="userName"
                :rules="rules.name"
              >
                <el-input
                  placeholder="请输入用户名"
                  v-model="signInForm.userName"
                  clearable
                ></el-input>
              </el-form-item>
              <el-form-item
                label="密码："
                prop="password"
                :rules="rules.password"
              >
                <el-input
                  placeholder="请输入密码"
                  v-model="signInForm.password"
                  show-password
                ></el-input>
              </el-form-item>
              <br />
              <el-button
                type="primary"
                plain
                @click="signInSubmit"
                style="width: 140px"
                >登录</el-button
              >
              <div class="nextPage">
                <el-row>
                  <el-col style="text-align: left; width: 64px">
                    <el-button type="text" @click="toForgetPage"
                      >忘记密码</el-button
                    >
                  </el-col>
                  <el-col style="text-align: right; width: 150px">
                  </el-col>
                  <el-col style="text-align: right; width: 80px; float: right">
                    <el-button type="text" @click="toSignUpPage"
                      >我是新用户</el-button
                    >
                  </el-col>
                </el-row>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-form>

      <!-- 忘记密码 -->
      <el-form ref="forgetForm" label-width="126px" :model="forgetForm">
        <div id="forget" v-if="forgetPage">
          <el-button type="text" @click="toSignInPage">&lt; 返回登录</el-button>
          <el-row>
            <el-col :span="16" :offset="4">
              <div style="font-family: 庞门正道粗书体; font-size: 32px">
                找回密码
              </div>
            </el-col>
          </el-row>
          <br /><br /><br />
          <el-row>
            <el-col :span="16" :offset="3">
              <el-form-item
                prop="forget_email"
                label="注册邮箱："
                :rules="rules.email"
              >
                <el-input
                  placeholder="请输入注册邮箱"
                  v-model="forgetForm.forget_email"
                  clearable
                ></el-input>
              </el-form-item>
              <br /><br />
              <el-button type="primary" plain @click="sendCheckInformation"
                >发送验证信息</el-button
              >
            </el-col>
          </el-row>
        </div>
      </el-form>

      <!-- 注册 -->
      <el-form
        ref="signUpForm"
        label-width="126px"
        :model="signUpForm"
        :rules="rules"
      >
        <div id="signUp" v-if="signUpPage">
          <el-button type="text" @click="toSignInPage">&lt; 返回登录</el-button>
          <el-row>
            <el-col :span="16" :offset="3">
              <el-form-item label="用户名：" prop="signUpName">
                <el-input
                  placeholder="请输入用户名"
                  v-model="signUpForm.signUpName"
                  clearable
                ></el-input>
              </el-form-item>
              <el-form-item label="密码：" prop="signUpPass">
                <el-input
                  type="password"
                  v-model="signUpForm.signUpPass"
                ></el-input>
              </el-form-item>
              <el-form-item label="确认密码：" prop="checkPass">
                <el-input
                  type="password"
                  v-model="signUpForm.checkPass"
                ></el-input>
              </el-form-item>
              <el-form-item prop="signUp_email" label="注册邮箱：">
                <el-input
                  placeholder="请输入注册邮箱"
                  v-model="signUpForm.signUp_email"
                  clearable
                ></el-input>
              </el-form-item>
              <el-button
                type="primary"
                plain
                @click="signUpSubmit"
                style="width: 140px"
                >注册</el-button
              >
            </el-col>
          </el-row>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapMutations } from "vuex";
import {login} from '@/api/index'
import { register } from "@/api/index";
import { findUserByName } from "@/api/index";
// import router from '@/router';
export default {
  computed: {
    ...mapGetters(["checkLogin"]),
  },
  name: "LoginView",
  data() {
    // 注册密码校验
    var validatePass1 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.signUpForm.checkPass !== "") {
          this.$refs.signUpForm.validateField("checkPass");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.signUpForm.signUpPass) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      // 登录信息
      signInForm: {
        userName: "",
        password: "",
      },
      // 忘记密码的邮箱
      forgetForm: {
        forget_email: "",
      },
      // 注册信息
      signUpForm: {
        signUpName: "",
        signUpPass: "",
        checkPass: "",
        signUp_email: "",
      },
      rules: {
        /* 登录验证 */
        name: [
          { required: true, message: "请输入用户名", trigger: "blur" }, //其他规则待添加
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" }, //其他规则待添加
        ],
        /* 遗忘邮箱验证 */
        email: [
          { required: true, message: "请输入邮箱地址", trigger: "blur" },
          {
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"],
          },
        ],
        /* 注册验证 */
        signUpName: [
          { required: true, message: "请输入用户名", trigger: "blur" }, //其他规则待添加
        ],
        signUpPass: [{ validator: validatePass1, trigger: "blur" }],
        checkPass: [{ validator: validatePass2, trigger: "blur" }],
        signUp_email: [
          { required: true, message: "请输入邮箱地址", trigger: "blur" },
          {
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"],
          },
        ],
      },
      // 控制三个页面的显示
      signInPage: true,
      forgetPage: false,
      signUpPage: false,
    };
  },
  methods: {
    ...mapMutations({
      switchLoginState: "SWITCH_LOGIN_STATE",
    }),
    // 返回登录
    toSignInPage() {
      this.forgetPage = false;
      this.signUpPage = false;
      this.signInPage = true;
    },
    // 忘记密码
    toForgetPage() {
      this.signInPage = false;
      this.signUpPage = false;
      this.forgetPage = true;
    },
    // 我是新用户
    toSignUpPage() {
      this.signInPage = false;
      this.forgetPage = false;
      this.signUpPage = true;
    },
    // 登录
    signInSubmit() {
      // this.$refs["signInForm"].validate((valid) => {
      //   if (valid) {
      //     login({
      //       "name":this.$data.signInForm.userName,
      //       "&password":this.$data.signInForm.password
      //     }).then(res=>{
      //       router.push({path:'/'});
      //       console.log("登陆的返回",res,this.$data.signInForm)
      //     })
      //   } else {
      //     console.log("请输入正确的用户名和密码");
      //     return false;
      //   }
      // });
      login({name:"1",password:"1"}).then(res=>{
            console.log("登陆的返回",res,this.$data.signInForm)
          })
    },
    // 忘记密码发送验证信息
    sendCheckInformation() {
      this.$refs["forgetForm"].validate((valid) => {
        if (valid) {
          /* 这里可以写发邮箱的代码 */
          this.$message({
            message:
              "发送验证信息成功，三个工作日内将会收到验证信息，请耐心等待",
            type: "success",
          });
        } else {
          console.log("请输入正确的邮箱地址");
          return false;
        }
      });
    },
    // 注册
    signUpSubmit() {
      this.$refs["signUpForm"].validate((valid) => {
        if (valid) {
          findUserByName(this.$data.signUpForm.signUpName).then(res=>{
            console.log(res)
          })
          register(this.$data.signUpForm).then(res=>{
            console.log(res)
            this.$message({ message: "注册成功", type: "success" });
            this.toSignInPage();
          })
        } else {
          console.log("请按要求填写");
          return false;
          
        }
      });
    }
  },
};
</script>

<style>
/* 背景 */
.login {
  width: 1440px;
  min-height: 100vh;
  background: linear-gradient(
    to right,
    #fff 0%,
    #fff 50%,
    #3894ff 50%,
    #3894ff 100%
  );
  display: flex;
  justify-content: center;
  align-items: center;
}
/* 登录框 */
.loginBox {
  padding-top: 60px;
  height: 360px;
  width: 670px;
  background-color: #fff;
  box-shadow: -3px 2px 9px 7px #d5cfcf;
  border-radius: 50px;
}
.loginBox img {
  width: 290.5px;
  height: 46.9px;
}
.loginBox .el-button {
  font: 16px "方正楷体";
}
/* 返回登录 */
#forget .el-button,
#signUp .el-button {
  margin-left: 50px;
}
/* el-form-item lable */
.el-form-item__label {
  font-family: "方正楷体" !important;
  font-size: 16px !important;
  font-weight: 600 !important;
}
</style>
