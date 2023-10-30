<template>
    <div>
        <div class="compHeader">
            <div class="main">
                <div class="header_l">
                    <a href="/index">
                        <img src="/static/images/logo_1.png" />
                        <img src="/static/images/logo_2.png" />
                    </a>
                    <span>www.xxx.com</span>
                </div>
                <div class="header_c">
                    <input name="" type="text" placeholder="请输入关键字..." class="keyword" />
                    <input name="" type="button" class="btnsearch" />
                </div>
                <div class="header_r">
                    <a href="/login"> {{ username }}</a>
                    <a href="/login/register">注册</a>
                </div>
            </div>
        </div>

        <div class="jobtypeBox">
            <div class="DivLeft">
                <div class="DivCompany">
                    <div class="DivCompany_img"><img :src="companyIcon" width="180" /></div>
                    <h1><a :href="'/info/company?companyId='+this.companyId" >{{ companyName }}</a><img src="/static/images/16.png" /></h1>
                    <div class="clear"></div>
                    <div class="DivLeft_2">
                        <p>行业：{{ companyField }}</p>
                        <p>性质：{{ companyType }}</p>
                        <p>规模：{{ companyScale }}</p>
                        <p>企业主页：<a href="">{{ companyHome }}</a></p>
                        <p>详细地址：{{ companyAddress }}</p>
                        <p>{{ companyText }}</p>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
            <div class="DivRight">
                <div class="DivRight_2">
                    <div class="divJob_1">
                        <div class="divJob_l">
                            <div class="tit">
                                <h2>{{ recruitName }}</h2>
                                <div class="clear"></div>
                            </div>
                            <div class="bqbox">
                                <div v-for="(item, index)   in tagSplit" :key="index">
                                    <label>{{ item }}</label>
                                </div>
                                <div class="clear"></div>
                            </div>
                            <div class="jobsub">工作地点： {{ recruitAddress }}</div>
                            <div class="jobsub">职位月薪： {{ salary }}</div>
                            <div class="jobsub">招聘人数： {{ recruitNumber }}人</div>
                            <div class="jobsub">最低学历： {{ recruitDegree }}</div>
                            <div class="jobsub">年龄要求： {{ recruitAge }}</div>
                            <div class="jobsub">工作经验： {{ recruitExp }}</div>
                            <div class="jobsub">招聘方式： {{ recruitType }}</div>
                            <div class="jobsub">刷新日期：{{ formattedDate }}</div>
                        </div>
                    </div>
                    <div class="clear"></div>
                    <div class="divJob_2">
                    <button :class="applyButtonClass" @click="applyForRecruit">{{ applyStatus }}</button>
                    </div>
                    <div class="clear"></div>
                    <div class="divJob_3">
                        <b>岗位职责：</b><br />
                        <div class="con">
                            <div v-html="formatRecruitText(recruitTextDuty)"></div>
                        </div>
                        <b>岗位要求：</b><br />
                        <div class="con">
                            <div v-html="formatRecruitText(recruitTextNeed)"></div>
                        </div>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
        <div class="clear"></div>
        <div class="footer">
        </div>
    </div>
</template>
<script>
import axios, { Axios } from 'axios';
import Cookies from 'js-cookie';
export default {
    data() {
        return {
            isLogin: false,

            username:"",

            companyIcon: "",//企业图标
            companyName: "",//企业名称
            companyType: "",//企业性质
            companyField: "",//企业行业
            companyScale: "",//企业规模
            companyHome: "",//企业主页
            companyAddress: "",//企业地址
            companyText: "",//企业介绍
            companyId:"",

            recruitName: "",//职位名称
            recruitTag: "",//招聘标签
            recruitAddress: "",//职位地址
            salary: "",//薪水
            recruitNumber: "",//招聘人数
            recruitDegree: "",//招聘学历
            recruitAge: "",//招聘年龄
            recruitExp: "",//工作经验
            recruitType: "",//招聘方式
            recruitTime: "",//更新时间
            recruitTextDuty: "",//岗位职责
            recruitTextNeed: "",//岗位要求
            applyStatus: "立即申请",
            applyButtonClass: "divJob_2_1",
            cookieValue: ""
        }
    },
    methods: {
        applyForRecruit() {
            let that = this;
            const cookieValue = Cookies.get('cookieUserId');
            if (!this.isLogin) {
                alert("请先登陆再进行申请！")
            } else {
                //用户申请职位逻辑写于此
                axios({
                method: "post",
                url: '/api/apply',
                data: {
                    userId: cookieValue,
                    companyId: that.companyId,
                    recruitId: that.recruitId
                }
            })
                .then(response => {
                    let data = response.data
                    let code = data.code
                    let msg = data.msg
                    let info = data.object
                    if (code === 300001) {
                        console.log(data)
                        alert(msg)
                        that.applyStatus = "已申请"
                        that.applyButtonClass = "divJob_2_0"
                    } else {
                        console.log(data)
                        alert(msg)
                    }
                })

            }
        },
        formatRecruitText(text) {
            return text.replace(/\n/g, '<br>');
        },
    },
    mounted() {
        if (this.$cookie.get('cookieUserName') != null && this.$cookie.get('cookieUserName') != "") {
            this.isLogin = true
        }
        if(!this.isLogin){
            alert("请登陆")
            window.location.href = "/index"
        }

        this.username = this.$cookie.get("cookieUserName")
        let that = this;
        this.companyId = this.$route.query.companyId
        this.recruitId = this.$route.query.recruitId
        var userId = this.$route.query.userId
        this.cookieValue = Cookies.get('cookieUserId');
        axios({
            method: 'GET',
            url: '/api/company/getCompanyInfo/' + that.companyId //你的后端路径
        })
            .then(response => {
                let data = response.data
                let code = data.code
                let msg = data.msg
                let info = data.object
                if (code === 200001) { //判断你的请求是否成功
                    console.log(data)
                    that.companyIcon = require('@/../dist/static/images/' + info.companyIcon)
                    that.companyName = info.companyName
                    that.companyType = info.companyType
                    that.companyField = info.companyField
                    that.companyScale = info.companyScale
                    that.companyHome = info.companyHome
                    that.companyAddress = info.companyAddress
                    that.companyText = info.companyText
                } else {
                    alert(msg)
                }
            }, error => {
                console.log('错误', error.message)
                // alert(error.message)
            }),
            axios({
                method: 'GET',
                url: '/api/recruit/getRecruitInfo/' + that.companyId + '/' + that.recruitId + '/' + that.cookieValue
            })
                .then(response => {
                    let data = response.data
                    let code = data.code
                    let msg = data.msg
                    let info = data.object
                    if (code === 200001) { //判断你的请求是否成功
                        console.log(data)
                        that.recruitName = info.recruitName
                        that.recruitTag = info.recruitTag
                        that.recruitAddress = info.recruitAddress
                        that.salary = info.recruitSalaryMin + "K-" + info.recruitSalaryMax + "K[参考工资]"
                        that.recruitNumber = info.recruitNumber
                        that.recruitDegree = info.recruitDegree
                        that.recruitAge = info.recruitAge
                        that.recruitExp = info.recruitExp
                        that.recruitType = info.recruitType
                        that.recruitTime = new Date(info.recruitTime)
                        that.recruitTextDuty = info.recruitTextDuty
                        that.recruitTextNeed = info.recruitTextNeed
                        if (info.userId != null) {
                            that.applyStatus = "已申请"
                            that.applyButtonClass = "divJob_2_0"
                        }
                    } else {
                        alert(msg)
                    }
                }, error => {
                    console.log('错误', error.message)
                    // alert(error.message)
                })
        
    },
    computed: {
        formattedDate() {
            const date = new Date(this.recruitTime);
            return date.toLocaleString();
        },
        tagSplit() {
            const tags = this.recruitTag.split("#")
            return tags;
        }
    },
}
</script>
