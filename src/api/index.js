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

/* 组队相关 */
// 展示所有队伍的招募信息(无筛选条件)
//  /teamForum/showAllForum/page    GET 参数： pageNum
export const showAllForum = (data)=>Service({url:`/teamForum/showAllForum/page?pageNum=${data}`,method:'get'})

// 添加队伍成员 用户通过论坛,看见招募信息,点击申请入队,发送信息到队长的信息栏,队长确认后,点击同意入队)
// memberName 申请入队人名称       fid 招募贴id     post
export const addTeamMember = (memberName,fid)=>Service({url: `/team/addMembers?memberName=${memberName}+&fid=${fid}`,method:'post'})

// 发布招募信息
// post 参数:队伍名 队伍参赛名 队伍需求(非必选) 队伍人数(非必选) 参赛类型(非必选) 是否公开(公开 未公开) 队长联系方式 队伍相关图片
export const releaseTeam = (data)=>Service({
    url:`/teamForum/PublishRecruitmentInfo
        ?t_name=${data.t_name}
        &t_gname=${data.t_gname}
        &t_demand=${data.t_demand}
        &t_count=${data.t_count}
        $t_type=${data.t_type}
        &t_public=${data.t_public}
        &t_contact=${data.t_contact}
        image=${data.image}`,
    method:"post"
})

// 展示所有比赛信息
// /game/showGame/page
export const showMatch = (data)=>Service({url:`/game/showGame/page?pageNum=${data}`,method:'get'})

//收藏比赛
// /game/addCollect   POST 参数 无
export const addCollect = ()=>Service({url:`/game/addCollect`,method:'post'})

// 查询比赛
export const queryMatch = (name,pageNum)=>Service({
    url:`/game/queryGameByName/page?name=${name}&pageNum=${pageNum}`,
    method:'get'
})