import axios from 'axios'
import { Loading } from 'element-ui';
import { Message } from 'element-ui';

let loadingObj = null
const Service = axios.create({
    baseURL:'/api',
    timeout:5000
});
//请求拦截
Service.interceptors.request.use((config)=>{
    loadingObj = Loading.service({
        lock: true,
        text: 'Loading',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
        });
    return config; 
});

//响应拦截
Service.interceptors.response.use((res)=>{
    loadingObj.close();
    return res.data;
},(error)=>{
    loadingObj.close();
    Message({
        duration:2000,
        message: '服务器错误',
        type: 'error'
        });
    console.log(error)
    return Promise.reject(new Error('faile'));
});

export default Service;