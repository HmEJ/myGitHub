<template>
    <div>
        <div class="header">
            <div class="mainwarp">
                <div class="logo"><a href="/index"><img src="/static/images/log.png" /></a></div>
                <div class="welcome">欢迎填写一分钟简历</div>
                <div class="headerright">
                    <a href="/personal/resume">{{ userName }}</a>
                    <span>|</span>
                    <a href="/index">返回首页</a>
                </div>
            </div>
        </div>
        <div class="jianliBox">
            <div class="jlpart1">
                <img src="/static/images/img1.png" />
            </div>
            <div class="jlpart2">
                <div class="jlpart2_1">
                    <div class="jlbox1">
                        <h1>一分钟填写简历</h1>
                        <p>填写以下信息即可生成一份简历，一步注册，起航你的成功之梦！</p>
                    </div>
                    <div class="jlbox2">
                        <span class="tit">姓名</span>
                        <input :disabled="isShow" name="" v-model="resumeName" type="text" class="txtcon txt1" />
                    </div>
                    <div class="jlbox2">
                        <span class="tit">我是</span>
                        <div v-show="isManShow" class="sex" :class="{ on: show_sex }" @click="showSex('man')">男</div>
                        <div v-show="isWoManShow" class="sex" :class="{ on: !show_sex }" @click="showSex('girl')">女</div>
                        <div class="clear"></div>
                    </div>
                    <div class="jlbox2">
                        <span class="tit">出生于</span>
                        <input :disabled="isShow" name="" v-model="resumePlace" type="text" class="txtcon txt3" />
                        <div class="clear"></div>
                    </div>
                    <div class="jlbox2">
                        <span class="tit">手机号</span>
                        <input :disabled="isShow" name="" v-model="resumePhone" type="text" class="txtcon txt3" />
                        <div class="clear"></div>
                    </div>
                    <div class="jlbox2">
                        <span class="tit">毕业学校</span>
                        <input :disabled="isShow" name="" v-model="resumeSchool" type="text" class="txtcon txt3" />
                        <select :disabled="isShow" v-model="resumeDegree" @change="updateResumeDegree()" class="txtcon txt5">
                            <option value="0">高中</option>
                            <option value="1">专科</option>
                            <option value="2">本科</option>
                            <option value="3">硕士</option>
                            <option value="4">博士</option>
                        </select>
                        <div class="titxt">学历</div>
                        <div class="clear"></div>
                    </div>
                    <div class="jlbox2">
                        <span class="tit">所学专业</span>
                        <input :disabled="isShow" name="" v-model="resumeMajor" type="text" class="txtcon txt3" />
                        <div class="clear"></div>
                    </div>
                    <div class="jlbox2">
                        <span class="tit">想在</span>
                        <select :disabled="isShow" v-model="resumeLoc" class="txtcon txt5">
                            <option value="0">北京</option>
                            <option value="1">上海</option>
                            <option value="2">深圳</option>
                            <option value="3">广州</option>
                            <option value="4">长春</option>
                            <option value="5">沈阳</option>
                            <option value="6">武汉</option>
                            <option value="7">杭州</option>
                            <option value="8">苏州</option>
                            <option value="9">西安</option>
                            <option value="10">大连</option>
                            <option value="11">南京</option>
                        </select>
                        <div class="titxt">找月薪</div>
                        <select :disabled="isShow" v-model="resumeSalary" class="txtcon txt6">
                            <option value="5">30000以上</option>
                            <option value="4">20000-30000</option>
                            <option value="3">10000-20000</option>
                            <option value="2">5000-10000</option>
                            <option value="1">5000以下</option>
                        </select>
                        <div class="titxt">的</div>
                        <select :disabled="isShow" v-model="resumeJobType" class="txtcon txt5">
                            <option value="0">全职</option>
                            <option value="1">兼职</option>
                            <option value="2">实习</option>
                        </select>
                        <div class="titxt">工作</div>
                        <div class="clear"></div>
                    </div>
                    <div class="jlbox2">
                        <span class="tit">求职状态</span>
                        <select :disabled="isShow" v-model="resumeStatus" class="txtcon txt2">
                            <option value="0">离职-随时到岗</option>
                            <option value="1">在职-月内到岗</option>
                            <option value="2">在职-考虑机会</option>
                            <option value="3">在职-暂不考虑</option>
                        </select>
                    </div>
                    <div class="jlbox2">
                        <span class="tit">邮箱</span>
                        <input :disabled="isShow" v-model="resumeEmail" name="" type="text" class="txtcon txt3" />
                        <div class="clear"></div>
                    </div>
                    <div class="jlbox2">
                        <input v-show="!isShow" @click="submitData()" name="" type="button" value="生成简历" class="jlbtn" />
                    </div>
                </div>
            </div>
        </div>

        <div class="clear"></div>
        <div class="footer">
        </div>
    </div>
</template>
<script>
import axios from 'axios'
export default {
    data() {
        return {
            show_sex: true,
            tbId: null,
            resumeId: null,
            userId: null,
            resumeName: null,
            resumeGender: 1,
            resumePlace: null,
            resumePhone: null,
            resumeSchool: null,
            resumeDegree: 0,
            resumeMajor: null,
            resumeLoc: 0,
            resumeSalary: 2,
            resumeJobType: 0,
            resumeStatus: 0,
            resumeEmail: null,

            userId: null,
            userName: null,

            isManShow: true,
            isWoManShow : true,
            isShow: false,
        }
    },
    methods: {
        showSex(sex) {
            if (sex == 'man') {
                this.show_sex = true
                this.resumeGender = 1
            } else {
                this.show_sex = false
                this.resumeGender = 0
            }
            console.log(this.resumeGender);
        },
        updateResumeDegree() {
            console.log(this.resumeDegree);
        },
        submitData() { //生成简历
            let that = this
            axios({
                method: 'post',
                url: '/api/resume/generateResume',
                data: {
                    tbId: that.tbId,
                    resumeId: that.resumeId,
                    userId: that.userId,
                    resumeName: that.resumeName,
                    resumeGender: that.resumeGender,
                    resumePlace: that.resumePlace,
                    resumePhone: that.resumePhone,
                    resumeSchool: that.resumeSchool,
                    resumeDegree: that.resumeDegree,
                    resumeMajor: that.resumeMajor,
                    resumeLoc: that.resumeLoc,
                    resumeSalary: that.resumeSalary,
                    resumeJobType: that.resumeJobType,
                    resumeStatus: that.resumeStatus,
                    resumeEmail: that.resumeEmail
                }
            })
                .then(function (result) {
                    console.log(result);
                    location.reload()
                })
        }
    },
    mounted() {
        // 进入页面判断是否存在简历，如果存在就回显否则
        this.userId = this.$cookie.get('cookieUserId');
        this.userName = this.$cookie.get('cookieUserName');

        if(this.userName==null || this.userName==''){
            alert("请先登陆!")
            window.location.href = "/index"
        }



        let that = this
        axios({
            method: "get",
            url: "/api/resume/acquireResume?userId=" + this.userId,
        })
            .then(function (result) {
                console.log(result);
                if (result.data.object != null) {
                    that.tbId = result.data.object.tbId;
                    that.userId = result.data.object.userId;
                    that.resumeId = result.data.object.resumeId;
                    that.resumeName = result.data.object.resumeName;
                    that.resumeGender = result.data.object.resumeGender;
                    that.resumePlace = result.data.object.resumePlace
                    that.resumePhone = result.data.object.resumePhone;
                    that.resumeSchool = result.data.object.resumeSchool;
                    that.resumeMajor = result.data.object.resumeMajor;
                    that.resumeLoc = result.data.object.resumeLoc;
                    that.resumeSalary = result.data.object.resumeSalary;
                    that.resumeStatus = result.data.object.resumeStatus;
                    that.resumeEmail = result.data.object.resumeEmail;

                    that.isShow = true
                    
                    if (that.resumeGender == 1) {
                        that.show_sex = true
                        that.isWoManShow = false
                    }else{
                        that.show_sex = false
                        that.isManShow = false
                    }


                }


            })
    }
}
</script>