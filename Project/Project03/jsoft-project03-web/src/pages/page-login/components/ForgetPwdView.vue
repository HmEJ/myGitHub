<template>
    <div>
        <div class="header">
            <div class="mainwarp">
                <div class="logo"><a href="/index"><img src="/static/images/log.png" /></a></div>
                <div class="welcome">重置密码</div>
                <div class="headerright">
                    <a href="/login">个人登录 </a>
                    <span>|</span>
                    <a href="/index">返回首页</a>
                </div>
            </div>
        </div>
        <div class="passdBox">
            <div class="passpart1">
                <div class="TitleNotice">忘记密码了？根据收到的激活码进行验证身份，通过验证后重置密码，您就可以登录了。</div>
                <div class="StepsBox">
                    <div id="step_div_1" class="Step stepfirst" :class="{ Active: show_1 }">
                        <span class="StepNo">1</span>
                        <span class="StepName">确认账户</span>
                    </div>
                    <div id="step_div_2" class="Step" :class="{ Active: show_2 }">
                        <span class="StepNo">2</span>
                        <span class="StepName">安全认证</span>
                    </div>
                    <div id="step_div_3" class="Step" :class="{ Active: show_3 }">
                        <span class="StepNo">3</span>
                        <span class="StepName">重置密码</span>
                    </div>
                    <div id="step_div_4" class="Step steplast" :class="{ Active: show_4 }">
                        <span class="StepNo"></span>
                        <span class="StepName">完成</span>
                    </div>
                </div>
                <div class="clear"></div>
                <div id="step_1" class="restInfo" v-show="show_1">
                    <div class="box1">
                        <span>账 户：</span>
                        <input name="" v-model="forgetArgs" type="text" placeholder="用户名/邮箱/已验证手机号" />
                        <div class="clear"></div>
                    </div>
                    <div class="box1 verify">

                        <span>人机校验：</span>
                        <br>
                        <br>
                        <SliderVerificationCode height="35px" sliderWidth="44px" inactiveValue=false activeValue=true
                            v-model="isSuccess" />

                    </div>
                    <div class="box1">
                        <input type="button" class="onebtn" @click="show(1)" value="下一步" />
                    </div>
                </div>
                <div id="step_2" class="restInfo" v-show="show_2">
                    <div class="box2"> {{ text }} </div>

                    <div class="box1">
                        <span>激活码：</span>
                        <input name="smsCodeInput" v-model="smsCodeInput" type="text" class="yzm" />
                        <b class="phonenum" @click="getSMSCode()">获取验证码</b>
                        <div class="clear"></div>
                    </div>
                    <div class="box1">
                        <input type="button" class="onebtn" @click="show(2)" value="下一步" />
                    </div>
                </div>
                <div id="step_3" class="restInfo" v-show="show_3">
                    <div class="box2">
                        <span class="span1">用户名：</span>
                        <span class="span2">{{ userName }}</span>
                        <div class="clear"></div>
                    </div>
                    <div class="box1">
                        <span>新密码：</span>
                        <input v-model="newPassword" name="" type="password" />
                        <div class="clear"></div>
                    </div>
                    <div class="box1">
                        <span>确认密码：</span>
                        <input v-model="sureNewPassword" name="" type="password" />
                        <div class="clear"></div>
                    </div>
                    <div class="box1">
                        <input type="button" class="onebtn" @click="show(3)" value="下一步" />
                    </div>
                </div>
                <div id="step_4" class="restInfo2" v-show="show_4">
                    <div class="SucTitle">恭喜您，您的密码设置成功！</div>
                    <p>您的用户名是：{{ userName }}</p>
                    <p>您可以使用新密码登录MYHD人才网<a href="/login">立即登录</a></p>
                </div>

            </div>
        </div>
        <div class="clear"></div>
    </div>
</template>
<script>
import axios from 'axios';
export default {
    name: 'forgetPwd',
    data() {
        return {
            show_1: true,
            show_2: false,
            show_3: false,
            show_4: false,
            imageUrl: '/static/images/VerifyCode.jpg',

            forgetArgs: "",
            imageVarifyCode: "",
            userId: null,
            phone: null,
            userEmail: null,
            userName: null,
            password: null,
            code: null,
            text: "111",
            smsCode: null,
            smsCodeInput: "",

            isSuccess: false,

            newPassword: "",
            sureNewPassword: ""
        }
    },
    methods: {
        onSuccess() {
            console.log("验证成功")
            this.isSuccess = true
        },
        onFail() {
            console.log("验证失败")
            this.isSuccess = false
        },
        onRefresh() {
            console.log("验证码刷新")
        },
        getSMSCode() {  //获取验证码
            let that = this
            axios({
                method: 'post',
                url: '/api/user/sendVerifyCode',
                data: {
                    userId: that.userId,
                    phone: that.phone,
                    userEmail: that.userEmail,
                    userName: that.userName,
                    password: that.password,
                    code: that.code
                }
            })
                .then(function (result) {
                    console.log(result.data)
                    that.smsCode = result.data
                })
        },
        getUserInfo() {  //获取用户详细信息
            let that = this
            axios({
                method: 'post',
                url: '/api/user-info/getUserInfo',
                data: {
                    userId: that.userId,
                    phone: that.phone,
                    userEmail: that.userEmail,
                    userName: that.userName,
                    password: that.password,
                    code: that.code
                }
            })
                .then(function (result) {
                    console.log(result)
                    that.phone = result.data.object.phone
                    console.log(result.data.object.userName)
                    that.userName = result.data.object.userName
                })
        },
        validateCode() {
            let that = this
            return new Promise((resolve, reject) => {
                axios({
                    method: 'post',
                    url: '/api/user/validateCode',
                    data: {
                        userId: that.userId,
                        phone: that.phone,
                        userEmail: that.userEmail,
                        userName: that.userName,
                        password: that.password,
                        code: that.smsCodeInput
                    }
                })
                    .then(function (result) {
                        console.log("验证码校验的返回值：" + result.data.code)
                        if (result.data.code == "100001") {
                            resolve(true);
                        } else {
                            resolve(false);
                        }
                    })

            })

        },
        setNewPassword() {
            let that = this
            return new Promise((resolve, reject) => {
                axios({
                    method: 'post',
                    url: '/api/user/changePassword',
                    data: {
                        tbId:null,
                        userId: that.userId,
                        userName: that.userName,
                        email: that.userEmail,
                        phone: that.phone,
                        password: that.sureNewPassword
                    }
                })
                    .then(function (result) {
                        console.log("修改密码的返回值：" + result.data.code)
                        if (result.data.code == "100001") {
                            resolve(true);
                        } else {
                            resolve(false);
                        }
                    })

            })

        },
        show(stepIndex) {
            switch (stepIndex) {
                case 1:
                    if (this.forgetArgs.trim() == "") {
                        alert("请输入完整信息！")
                        return;
                    }
                    if (!this.isSuccess) {
                        alert("请进行人机校验")
                        return
                    }

                    const phoneRegex = /^[1-9]\d{10}$/;

                    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

                    const usernameRegex = /^user_\d+$/;

                    if (!phoneRegex.test(this.forgetArgs) && !emailRegex.test(this.forgetArgs) && !usernameRegex.test(this.forgetArgs)) {
                        alert("请检查输入信息格式是否正确！")
                        return
                    }

                    if (phoneRegex.test(this.forgetArgs)) {
                        this.phone = this.forgetArgs;
                        this.text = "发送激活码到您的手机：" + this.phone + " 中"
                    }
                    if (emailRegex.test(this.forgetArgs)) {
                        this.userEmail = this.forgetArgs;
                        this.text = "发送激活码到您的邮箱：" + this.userEmail + " 中"
                    }

                    if (usernameRegex.test(this.forgetArgs)) {
                        this.userName = this.forgetArgs;
                        let that = this
                        //获取用户详细信息
                        this.getUserInfo()
                        that.text = "发送激活码到您的手机：" + that.phone + " 中"
                    }

                    this.show_1 = false
                    this.show_2 = true
                    break
                //cookie中存放用户编号和用户名
                case 2:

                    let that = this
                    this.validateCode().then(function (result) {
                        if (result) {
                            that.getUserInfo()
                            that.show_2 = false
                            that.show_3 = true
                        } else {
                            alert("验证码校验不通过")
                            return
                        }
                    })
                    break
                case 3:
                    if (!(this.newPassword == this.sureNewPassword)) {
                        alert("两次输入的密码不一致")
                        return
                    } else {
                        this.setNewPassword().then(function(result){
                            if(result.data.code == "100001"){
                                alert("修改成功")
                            }
                        })
                    }
                    this.show_3 = false
                    this.show_4 = true
                    break
                case 4:
                    break
            }
        }
    }
}
</script>
<style>
.verify {
    width: 300px;
    margin-left: 25px;
}
</style>