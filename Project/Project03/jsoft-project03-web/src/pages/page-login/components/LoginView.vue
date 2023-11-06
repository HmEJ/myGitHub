<template>
    <div>
        <div class="header">
            <div class="mainwarp">
                <div class="logo"><a href="/index"><img src="/static/images/log.png" /></a></div>
                <div class="welcome">欢迎登录</div>
                <div class="headerright">
                    <a href="/index">返回首页</a>
                </div>
            </div>
        </div>
        <div class="content">
            <div class="logtype"></div>
            <div class="clear"></div>
            <div class="logoDiv">
                <div class="logoLeft">
                    <div class="leftimg">
                        <img src="/static/images/log1.gif" />
                    </div>
                    <div class="leftcon">
                        <a href="" class="a1">手机触屏版</a>
                        <a href="" class="a2">Android客户端下载</a>
                        <a href="" class="a3">iPhone客户端下载</a>
                    </div>
                </div>
                <div class="logoRight">
                    <div class="normalLogin">
                        <div class="username">
                            <span></span>
                            <input name="loginArgs" v-model="loginArgs" type="text" placeholder="请输入用户名/邮箱/已验证手机号" />
                        </div>
                        <div class="password">
                            <span></span>
                            <input name="password" v-model="password" type="password" placeholder="请输入密码" />
                        </div>
                        <div class="autoLogo">
                            <a href="/login/forget" class="pasd">忘记密码？</a>
                        </div>
                        <div class="autoLogo">
                            <span class="CheckBox" :class="{ Yes: isActive }" @click="check()"></span>
                            <span>我已阅读并接受<a href="/login/user_agree">用户协议</a></span>
                        </div>
                        <div class="logobtn">
                            <a href="#" @click="submitFormDTO()">登 录</a>
                        </div>
                        <div class="zcbtn">
                            <a href="/login/register">还没有账号？立即注册</a>
                        </div>
                    </div>
                    <div class="weixinLogin">
                        <div class="weixinQr">
                            <img src="/static/images/showqrcode.jpg" width="150" class="useHelpcon" />
                            <p>使用微信扫描上方二维码</p>
                            <p class="useHelp useHelpcon">使用帮助</p>
                        </div>
                        <div class="zcbtn">
                            <a href="/login/register">还没有账号？立即注册</a>
                        </div>
                    </div>
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
            isActive: false,
            loginArgs: "",

            phone: null,
            userEmail: null,
            password: null,
            userName: null,
            code: null,
            userId: null
        }
    },
    methods: {
        check() {
            this.isActive = !this.isActive
        },
        submitFormDTO() {
            // 简单的手机号正则，匹配11位数字
            const phoneRegex = /^[1-9]\d{10}$/;
            // 简单的邮箱正则，匹配常见的邮箱格式
            const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
            // 简单的用户名正则，匹配以"user_"开头，后面跟着数字的格式
            const usernameRegex = /^user_\d+$/;

            if (phoneRegex.test(this.loginArgs)) {
                this.phone = this.loginArgs;
            }
            if (emailRegex.test(this.loginArgs)) {
                this.userEmail = this.loginArgs;
            }
            if (usernameRegex.test(this.loginArgs)) {
                this.userName = this.loginArgs;
            }

            if (!this.isActive) {
                alert("请阅读并同意用户协议再登陆！")
            } else {
                let that = this;
                axios({
                    method: 'post',
                    url: '/api/user/login',
                    async: false,
                    withCredentials: true,
                    data: {
                        phone: that.phone,
                        userEmail: that.userEmail,
                        userName: that.userName,
                        password: that.password,
                        userId: that.userId,
                        code: that.code
                    }
                })
                    .then(function (result) {
                        console.log(result)
                        let code = result.data.code
                        if (code == 100000) {
                            alert("用户不存在或密码错误")
                        } else {
                            // that.$router.push('/index')
                            window.location.href = "/index"
                        }
                    })
            }
        }
    }
    
}
</script>
