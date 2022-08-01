import axios from 'axios'
import { Loading } from 'element-ui';
import { config } from 'vue/types/umd';
import { Message } from 'element-ui';
//使用create创建axios实例
const loadingObj = null
const Service = axios.create({
    timeout:8000,
    baseURL:"http://localhost:8080/SSM__04__SSC",
    headers:{
        "Content-type":"application/json;charset=utf-8"
    }
})
//请求拦截-增加loading，对请求做统一处理
Service.interceptors.request.use(config=>{
    loadingObj = Loading.service({
        lock: true,
        text: 'Loading',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
    })
    return config
})
//响应拦截-对返回值做统一处理
Service.interceptors.response.use(response=>{
    loadingObj.close()
    return response.data
},error=>{
    loadingObj.close()
    Message({
        message:"服务器错误",
        type:'error',
        duration:2000
    })
})

//post请求
export const post=config=>{
    return Service({
        ...config,
        method:"post",
        data:config.data
    })
}
//get请求
export const get=config=>{
    return Service({
        ...config,
        method:"get",
        data:config.data
    })
}