<template>
    <div class="mainContainer1">
        <div class="mainCard">
            <span class="cardTitle">我管理的队伍</span>
            <div class="cardContainer" v-for="(index) in managedTeams" :key="index">
                <div class="cardItem">
                    <span class="itemTitle">队伍名称示例</span>
                    <div class="cardItem-right">
                        <div class="avatarClass">
                        <el-avatar icon="el-icon-user-solid"></el-avatar> 
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                    </div>
                    <el-button type="primary" plain >管理队伍人员</el-button>
                    </div>
                </div>
                <div class="cardItem">
                    <span class="itemTitle">队伍名称示例</span>
                    <div class="cardItem-right">
                        <div class="avatarClass">
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                    </div>
                    <el-button type="primary" plain >管理队伍人员</el-button>
                    </div>
                </div>
                <div class="cardItem">
                    <span class="itemTitle">队伍名称示例</span>
                    <div class="cardItem-right">
                        <div class="avatarClass">
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                    </div>
                    <el-button type="primary" plain >管理队伍人员</el-button>
                    </div>
                </div>
            </div>
        </div>
        <div class="mainCard">
            <span class="cardTitle">待招募的队伍</span>
            <div class="cardContainer">
                <div class="cardItem">
                    <span class="itemTitle">队伍名称示例</span>
                    <div class="cardItem-right">
                        <div class="avatarClass">
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                    </div>
                    <el-button type="warning" plain >发布信息</el-button>
                    </div>
                </div>
                <div class="cardItem">
                    <span class="itemTitle">队伍名称示例</span>
                    <div class="cardItem-right">
                        <div class="avatarClass">
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                    </div>
                    <el-button type="warning" plain >发布信息</el-button>
                    </div>
                </div>
                <div class="cardItem">
                    <span class="itemTitle">队伍名称示例</span>
                    <div class="cardItem-right">
                        <div class="avatarClass">
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                    </div>
                    <el-button type="warning" plain >发布信息</el-button>
                    </div>
                </div>
            </div>
        </div>
        <div class="mainCard">
            <span class="cardTitle">我加入的队伍</span>
            <div class="cardContainer">
                <div class="cardItem">
                    <span class="itemTitle">队伍名称示例</span>
                    <div class="cardItem-right">
                        <div class="avatarClass">
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                    </div>
                    <el-button type="success" plain >查看信息</el-button>
                    </div>
                </div>
                <div class="cardItem">
                    <span class="itemTitle">队伍名称示例</span>
                    <div class="cardItem-right">
                        <div class="avatarClass">
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                    </div>
                    <el-button type="success" plain >查看信息</el-button>
                    </div>
                </div>
                <div class="cardItem">
                    <span class="itemTitle">队伍名称示例</span>
                    <div class="cardItem-right">
                        <div class="avatarClass">
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                    </div>
                    <el-button type="success" plain >查看信息</el-button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name:"MyTeamView",
    data(){
        return{
            managedTeams:[{}]
            }
    }
}
</script>

<style>
.mainContainer1{
    height: 1600px;
    width: 1440px;
    background: #EFEFF4;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
}
.mainCard{
    width: 1356px;
    height: 474px;
    line-height: 20px;
    border-radius: 20px 20px 20px 20px;
    background-color: rgba(255, 255, 255, 100);
    text-align: center;
}
.mainCard .cardTitle{
    font-family: gogoCentest;
    width: 168px;
    height: 43px;
    color: rgba(16, 16, 16, 100);
    font-size: 28px;
    text-align: left;
    position: relative;
    left: -562px;
    top: 41px;
}
.cardContainer{
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: space-around;
    height: 380px;
    padding-top: 49px;
}
.cardItem{
    display: flex;
    align-items: center;
    justify-content: space-between;
    width: 1274px;
    height: 81px;
    line-height: 20px;
    border-radius: 4px 4px 4px 4px;
    background-color: rgba(255, 255, 255, 100);
    text-align: center;
    border: 1px solid rgba(220, 223, 230, 100);
}
.cardItem .itemTitle{
    padding-left: 32px;
}
.cardItem-right{
    display: flex;
    width: 500px;
    align-items: center;
    justify-content: space-around;
}
.cardItem-right .avatarClass{
    width: 260px;
    display: flex;
    justify-content: space-around;
}
</style>