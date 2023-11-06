<template>
    <div>
        <div class="header">
            <div class="mainwarp">
                <div class="logo"><a href="/index"><img src="/static/images/log.png" /></a></div>
                <div class="welcome">欢迎注册</div>
                <div class="headerright">
                    <a href="/login">个人登录</a>
                    <span>|</span>
                    <a href="/index">返回首页</a>
                </div>
            </div>
        </div>
        <div class="zhuceBox">
            <div class="zcLeft">
                <ul id="tabzc">
                    <li>邮箱/手机号注册</li>
                    <li></li>
                    <div class="clear"></div>
                </ul>
                <div class="clear"></div>
                <div id="contentzc">
                    <ul style="display:block;">
                        <div class="zcpart0">
                            <div class="zcpart5">
                                <span></span>
                                <input name="" v-model="formArgs" type="text" placeholder="手机号或邮箱" />
                            </div>
                            <div class="zcpart6">手机号/邮箱用于登录和找回密码</div>
                            <div class="zcpart7">
                                <span></span>
                                <input name="" v-model="password" type="text" placeholder="设置密码" />
                            </div>
                            <div class="zcpart6">请输入6-20个字符</div>
                            <div class="zcpart2_c">
                                <span class="CheckBox" :class="{Yes: isShow}" @click="check()"></span>
                                <span style="float:left">我已阅读并接受<a href="/login/user_agree">用户协议</a></span>
                            </div>
                            <div class="clear"></div>
                            <div class="zcpart8">
                                <a href="#" @click="register()" >注 册</a>
                            </div>
      
                        </div>
                    </ul>
                </div>
            </div>
            <div class="zcRight">
                <div class="topdiv">
                    <span class="noPa">已有账号了？</span><br />
                    <a href="/login">立即登录</a>
                </div>
            </div>
        </div>
        <div class="clear"></div>
    </div>
</template>
<script>
import axios from 'axios'
export default {
    data() {
        return {
            isShow: false,

            formArgs:"",

            tbId:null,
            userId:null,
            userName:null,
            email:null,
            phone:null,
            password:""


        }
    },
    methods: {
        check() {
            this.isShow = !this.isShow
        },
        register(){
            if((this.formArgs == "") || (this.password == "")){
                alert("请填写完整信息");
            }else{
                // 简单的手机号正则，匹配11位数字
                const phoneRegex = /^[1-9]\d{10}$/;
                // 简单的邮箱正则，匹配常见的邮箱格式
                const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
                // 密码正则
                const psswordRegex = /^[a-zA-Z0-9]{6,20}$/;

                if(phoneRegex.test(this.formArgs)){
                    this.phone = this.formArgs;
                }else if(emailRegex.test(this.formArgs)){
                    this.email = this.formArgs;
                }else{
                    alert("请输入正确的手机号或邮箱");
                    return;
                }

                if(psswordRegex.test(this.password)){
                    this.password = this.password;
                }else{
                    alert("请输入正确格式的密码！")
                    return;
                }

                if(!this.isShow){
                    alert("请阅读并接受协议后再注册用户！")
                }else{
                    let that = this;
                    axios({
                        method:'post',
                        url:'/api/user/register',
                        async:true,
                        data:{
                            tbId:that.tbId,
                            userId:that.userId,
                            userName:that.userName,
                            email:that.email,
                            phone:that.phone,
                            password:that.password
                        }
                    })
                    .then(function (result){
                        console.log(result)
                        if(result.data.code == 100001){
                            alert("注册成功！");
                        }else{
                            alert("注册失败！");
                        }
                        // that.$router.push({path:'/login'})
                        window.location.href="/login";
                    })
                }
            }
        }
    }
}
</script>