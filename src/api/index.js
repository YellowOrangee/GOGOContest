//对所有的API进行统一管理
import Service from './Service'

//登录接口
//  /user/login POST 参数：name password
export const login = (data)=>Service({url:`/user/login?name=${data.userName}`+`&password=${data.password}`,method:'post'})


//注册接口
//   /user/register  POST 参数：name password email
export const register = (data)=>Service({url:`/user/register?name=${data.signUpName}`+`&password=${data.signUpPass}`+`&email=${data.signUp_email}`,method:'post'})

//检测用户名接口
//   /user/findUserByName  POST 参数：name 
export const findUserByName = (data)=>Service({url:`/user/findUserByName?name=${data}`,method:'get'})


//检测邮箱接口
//   /user/findUserByName  POST 参数：name 
export const judgeEmail = (data)=>Service({url:`/user/judgeEmail?email=${data}`,method:'get'})


//获取用户信息接口
//   /user/showInfo  GET 
export const showInfo = ()=>Service({url:'/user/showInfo',method:'get'})


//修改用户信息接口
//   /user/setInfo  POST 参数：name 
export const setInfo = (data)=>Service({url:`/user/setInfo?`,data,method:'post'})

// 展示所有队伍的招募信息(无筛选条件)
//  /teamForum/showAllForum/page    GET 参数： pageNum
export const showAllForum = (data)=>Service({url:`/teamForum/showAllForum/page?pageNum=${data}`,method:'get'})

// 展示所有比赛信息
// /game/showGame/page
export const showMatch = (data)=>Service({url:`/game/showGame/page?pageNum=${data}`,method:'get'})

//收藏比赛
// /game/addCollect   POST 参数 无
export const addCollect = ()=>Service({url:`/game/addCollect`,method:'post'})