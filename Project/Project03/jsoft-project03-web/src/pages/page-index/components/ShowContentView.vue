<template>
    <div>
        <div class="topDiv">
        </div>
        <div class="loginDiv">
            <div class="loginL">
                <img src="/static/images/LOGO.png" />
                <img src="/static/images/view_logo40.png" />
            </div>
            <div class="loginAddress">
                <div class="add1">
                </div>
            </div>
            <div class="loginC">
                <input v-model="word" name="" type="text" placeholder="请输入要查询的职位名称..." />
                <button @click="jumpSearchJob()"></button>
            </div>
            <div class="loginR">
                <img src="/static/images/spirit_40.png" />
            </div>
        </div>
        <div class="menuDiv">
            <a href="/index" class="onnav">首页</a>
            <a @click="jumpUserCenter()" href="#">个人中心</a>
            <a @click="jumpMyJob()" href="#">我的职位</a>
        </div>
        <div class="BodyMain">
            <div class="mainTop">
                <div class="mainTopL">
                    <ul>
                        <div class="items">
                            <div class="itemOne">
                                <span class="itme1" ><b></b> <div @click="handleItemClike(item.jobFiled)" class="horizonal" v-for="(item,index) in fields1" :key="index">{{ item.filedName }} </div> </span>
                            </div>
                        </div>
                        <div class="items">
                            <div class="itemOne">
                                <span class="itme2"><b></b><div @click="handleItemClike(item.jobFiled)" class="horizonal" v-for="(item,index) in fields2" :key="index">{{ item.filedName }} </div></span>
                            </div>
                        </div>
                        <div class="items">
                            <div class="itemOne">
                                <span class="itme3"><b></b><div @click="handleItemClike(item.jobFiled)" class="horizonal" v-for="(item,index) in fields3" :key="index">{{ item.filedName }} </div></span>
                            </div>
                        </div>
                        <div class="items">
                            <div class="itemOne">
                                <span class="itme4"><b></b><div @click="handleItemClike(item.jobFiled)" class="horizonal" v-for="(item,index) in fields4" :key="index">{{ item.filedName }} </div></span>
                            </div>
                        </div>
                        <div class="items">
                            <div class="itemOne">
                                <span class="itme5"><b></b><div @click="handleItemClike(item.jobFiled)" class="horizonal" v-for="(item,index) in fields5" :key="index">{{ item.filedName }} </div></span>
                            </div>
                        </div>
                        <div class="items">
                            <div class="itemOne">
                                <span class="itme6"><b></b><div @click="handleItemClike(item.jobFiled)" class="horizonal" v-for="(item,index) in fields6" :key="index">{{ item.filedName }} </div></span>
                            </div>
                        </div>
                        <div class="items">
                            <div class="itemOne">
                                <span class="itme7"><b></b><div @click="handleItemClike(item.jobFiled)" class="horizonal" v-for="(item,index) in fields7" :key="index">{{ item.filedName }} </div></span>
                            </div>
                        </div>
                    </ul>
                </div>
                <div class="mainTopR">
                    <div class="ads">
                        <div class="floatL" id="left"></div>
                        <div class="adBox0">
                            <ul style="margin-left: 0px; overflow: hidden">
                                <li>
                                    <a href="javascript:void(0)">
                                        <div
                                            style="background:url(/static/images/1.jpg);background-size: cover;background-repeat: no-repeat;background-position: center">
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="javascript:void(0)">
                                        <div
                                            style="background:url(/static/images/2.jpg);background-size: cover;background-repeat: no-repeat;background-position: center">
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="javascript:void(0)">
                                        <div
                                            style="background:url(/static/images/3.gif);background-size: cover;background-repeat: no-repeat;background-position: center">
                                        </div>
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <div class="clear"> </div>
                    </div>
                    <div class="login">
                        <div class="erweima">
                            <div class="close"></div>
                            <div class="ewmimg"><img src="/static/images/showqrcode.jpg" /></div>
                            <div class="ewmtxt">使用微信扫描上方二维码登录</div>
                        </div>
                        <div class="logintop">
                            <div class="loginC loginA select">
                                <span>{{ title }}</span>
                            </div>
                            <div class="loginC loginB">
                            </div>
                        </div>
                        <div class="loginmain">
                            <div v-show="isShow" class="ContentA">
                                <div class="txtInput"><input v-model="loginArgs" name="" type="text"
                                        placeholder="请输入用户名/手机号/邮箱" /></div>
                                <div class="txtInput"><input v-model="password" name="" type="text" placeholder="请输入密码" />
                                </div>
                                <div class="txtbtn">
                                    <div style="float:left">
                                        <br />
                                        <div class="clear"></div>
                                        <a href="/login/forget" class="a2">忘记密码</a>
                                    </div>
                                    <input @click="submitFormDTO()" name="" type="button" class="lobtn" value="登  录" />
                                </div>
                                <div class="zcbox">
                                    <div class="zc2">
                                        <a href="/login/resume" class="a1">一分钟填写简历</a>
                                        <a href="/login/register" class="a2">注册</a>
                                    </div>
                                </div>
                            </div>
                            <div v-show="!isShow" class="ContentA">
                                <span class="bigFont">用户名：</span>{{ userName }} <br />
                                <span class="bigFont">手机号：</span>{{ phone }} <br />
                                <span class="bigFont">邮箱：</span>{{ userEmail }} <br />
                                <br>
                                <a href="/login/resume" class="a1">一分钟填写简历</a>
                                <br>
                                <br>
                                <a @click="logOut()" href="#">退出登陆</a>
                            </div>

                        </div>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
            <div class="clear"></div>
            <div class="mainCon2">
                <div class="commontitdiv">
                    <div class="title">高薪职位</div>
                    <span>高薪就这么任性！</span>
                </div>
                <div class="clear"></div>
                <div class="highMoney">
                    <div v-for="item in HighRecruitList" :key="item.companyId+'-'+item.recruitId">
                        <div class="lineBox"></div>
                        <div class="divBox">
                            <a :href="'/info?companyId='+item.companyId+'&recruitId='+item.recruitId" ><img :src="'/static/images/'+item.company.companyIcon" width="84" /></a>
                            <p class="tit"><a :href="'/info?companyId='+item.companyId+'&recruitId='+item.recruitId">{{ item.recruitName }}</a></p>
                            <p class="sub"><a :href="'/info/company?companyId='+item.companyId" >{{item.companyName}}</a></p>
                            <p class="num"><span>{{ item.recruitSalaryMin }}K-{{ item.recruitSalaryMax}}K</span></p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="clear"></div>
        </div>
        <div class="footerLine"></div>
        <div class="footer">
            <div class="footerdown">
            </div>
        </div>
    </div>
</template>
<script>
import axios from 'axios';
export default {
    // edit by JoneElmo
    name: 'showContent',
    data() {
        return {
            arr: new Array(10),

            isShow: true,
            loginArgs: "",
            title: "个人登陆",

            phone: null,
            userEmail: null,
            password: null,
            userName: null,
            code: null,
            userId: null,

            isLogin: false,
            //模糊查询关键字
            word: "",

            HighRecruitList:"",
            // 领域列表 每一行的信息
            fields1:[],
            fields2:[],
            fields3:[],
            fields4:[],
            fields5:[],
            fields6:[],
            fields7:[],
            //点击领域后获取的值，将会传给后端做查询
            jobFiled:null

        }
    },
    mounted() {
        this.init()
        this.getJobFields()
    },
    methods: {
        init() {
            //判断用户是否登陆 
            const userId = this.$cookie.get("cookieUserId")
            const userEmail = this.$cookie.get("cookieUserEmail")
            const userName = this.$cookie.get("cookieUserName")
            const userPhone = this.$cookie.get("cookiePhone")
            // console.log(userName+";"+userId+";"+userEmail+";"+userPhone)
            console.log(this.$cookie.get())
            if (userName != null && userName != "") {
                console.log("用户已登陆")
                this.isLogin = true
                this.title = "个人信息"
                this.isShow = false;
                this.userName = userName
                if (userPhone == null || userPhone == "") {
                    this.phone = "暂未绑定手机号"
                } else {
                    this.phone = userPhone
                }
                if (userEmail == null || userEmail == "") {
                    this.userEmail = "暂未绑定邮箱"
                } else {
                    this.userEmail = userEmail
                }
            }
        },
        handleItemClike(jobFiled){
            console.log("获取到的工作领域是"+jobFiled)
            this.jobFiled = jobFiled

            window.location='/index/search?jobFiled=' + this.jobFiled;
        },
        getJobFields(){
            let that = this
            axios({
                method: 'get',
                url: '/api/recruit/get_fields',
            })
            .then(function (result){
                console.log("获取到的领域列表信息：")
                console.log(result.data.object)
                that.fields1 = result.data.object[0]
                that.fields2 = result.data.object[1]
                that.fields3 = result.data.object[2]
                that.fields4 = result.data.object[3]
                that.fields5 = result.data.object[4]
                that.fields6 = result.data.object[5]
                that.fields7 = result.data.object[6]
            })
        },
        jumpUserCenter() {
            if (this.isLogin) {
                window.location = '/personal/resume'
            } else {
                window.location = '/login'
            }
        },
        jumpMyJob() {
            if (this.isLogin) {
                window.location = '/personal/select'
            } else {
                window.location = '/login'
            }
        },
        jumpSearchJob() {
            window.location = '/index/search?word=' + this.word;
        },
        getUserInfo() {  //获取用户详细信息
            console.log("进入getUserInfo方法")
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
                    console.log("getUserInfo方法返回值" + result)
                    that.phone = that.$cookie.get("cookiePhone")
                    console.log("cookie中获取的手机号" + that.phone)

                    that.userName = that.$cookie.get("cookieUserName")
                    console.log("cookie中获取的用户名" + that.userName)

                    that.userEmail = that.$cookie.get("cookieUserEmail")
                    console.log("cookie中获取的邮箱" + that.userName)

                    if (that.phone == null || that.phone == "") {
                        that.phone = "暂未绑定手机号"
                    }
                    if (that.userEmail == null || that.userEmail == "") {
                        that.userEmail = "暂未绑定邮箱"
                    }

                })

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

            let that = this;
            axios({
                method: 'post',
                url: '/api/user/login',
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
                    console.log("submitFormDTO方法返回值" + result)
                    let code = result.data.code
                    if (code == 100000) {
                        alert("用户不存在或密码错误")
                    } else {
                        that.isShow = false
                        that.title = "个人资料"
                        console.log("调用getUserInfo方法。。。")
                        that.getUserInfo()
                        //刷新网页
                        location.reload()

                    }
                })

        },
        logOut() {
            // 获取所有Cookie的名称
            const cookieNames = Object.keys(this.$cookie.get());
            // 清空所有Cookie
            cookieNames.forEach(cookieName => {
                this.$cookie.remove(cookieName);
            });
            location.reload()
        }
    }
}
</script>
<style>
.bigFont {
    font-size: 15px;
    font-weight: bold;
}
.horizonal{
    display: inline;
}
</style>