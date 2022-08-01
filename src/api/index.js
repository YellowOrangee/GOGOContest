//对所有的API进行统一管理
import {post} from './Service'

//登录接口
//  /user/login POST 参数：name password

export const login =data=>{
    return post({
        url:'/user/login',
        data
    })
}

