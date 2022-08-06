//对所有的API进行统一管理
import Service from './Service'

//登录接口
//  /user/login POST 参数：name password
export const login = (data)=>Service({url:'/user/login',data,method:'post',})


//注册接口
//   /user/register  POST 参数：name password email
export const register = (data)=>Service({url:'/user/register',data,method:'post',})

//检测用户名接口
//   /user/findUserByName  POST 参数：name 
export const findUserByName = (data)=>Service({url:'/user/findUserByName',data,method:'post',})

//检测用户名接口
//   /user/showInfo  POST 参数：name 
export const showInfo = (data)=>Service({url:'/user/showInfo',data,method:'post',})
