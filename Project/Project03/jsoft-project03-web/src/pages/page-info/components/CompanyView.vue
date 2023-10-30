<template>
    <div>
        <div class="compMain">
            <div class="main">
                <div class="CpLogo">
                    <img :src="companyIcon" />
                </div>
                <div class="CpDetail">
                    <div class="CompanyName">
                        <span class="title">{{ companyName }}</span>
                        <img src="/static/images/16.png" />
                        <span class="guanzhu" @click="follow" v-if="followStatus">+ 关注</span>
                        <span class="ygz" @click="unfollow" v-if="!followStatus">已关注</span>
                    </div>
                    <p>{{ companyType }} | {{ companyScale }} | {{ companyField }}</p>
                    <p><a href="http://www.baidu.com">{{ companyHome }}</a></p>
                    <p>{{ companyAddress }}</p>
                </div>
            </div>
        </div>
        <div class="compcontent">
            <div class="comcontentL">
                <ul id="tabcompany">
                    <li :class="{ Active: isActive }" @click="show(1)">公司介绍</li>
                    <li :class="{ Active: !isActive }" @click="show(2)">招聘职位</li>
                    <div class="clear"></div>
                </ul>
                <div class="clear"></div>
                <div id="contentcompany">
                    <ul :style="status1">
                        <div class="conts">
                            <p>{{ companyText }}
                            </p>
                        </div>
                    </ul>
                    <ul :style="status2">
                        <div class="zhiwei" v-for="item in arr" :key="item.recruitId">
                            <div class="zhiweiL">
                                <div class="zw1">
                                    <a :href="'/info?companyId='+item.companyId+'&recruitId='+item.recruitId">{{ item.recruitName }}</a>
                                    <span>[{{ item.recruitAddress }}]</span>
                                </div>
                                <div class="zw2">
                                    <span class="red">月薪{{ item.recruitSalaryMin }}K-{{ item.recruitSalaryMax }}K</span> |
                                    招聘{{ item.recruitNumber }}人 | {{ item.recruitType }} | {{ item.recruitAge }} | {{
                                        item.recruitExp }} | {{ new Date(item.recruitTime).toLocaleString() }}刷新
                                </div>
                                <div class="zw3">
                                    岗位职责： {{ item.recruitTextDuty }}
                                </div>
                                <div class="zw3">
                                    岗位要求： {{ item.recruitTextNeed }}
                                </div>
                            </div>
                            <div class="zhiweiR">
                                <a href=# :class="{'ysq':item.userId != null}" @click="apply(item)">{{ item.userId == null ? '立即申请' : '已申请' }}</a>
                            </div>
                        </div>  
                    </ul>
                </div>
            </div>
        </div>

        <div class="clear"></div>
        <div class="footer">
        </div>
    </div>
</template>
<script>
import axios from 'axios';
import Cookies from 'js-cookie';
export default {
    data() {
        return {
            arr: "",
            isActive: true,
            status1: "display: block",
            status2: "display: none",
            companyIcon: "",//企业图标
            companyName: "",//企业名称
            companyType: "",//企业性质
            companyField: "",//企业行业
            companyScale: "",//企业规模
            companyHome: "",//企业主页
            companyAddress: "",//企业地址
            companyText: "",//企业介绍
            followStatus: true,
            followButtonClass: "guanzhu"
        }
    },
    methods: {
        show(i) {
            if (i == 1) {
                this.isActive = true
                this.status1 = "display:block"
                this.status2 = "display: none"
            } else {
                this.isActive = false
                this.status2 = "display:block"
                this.status1 = "display: none"
            }
        },
        /* 申请职位方法 */
        apply(item) {
            let that = this;
            const cookieValue = Cookies.get('cookieUserId');
            axios({
                method: "post",
                url: '/api/apply',
                data: {
                    userId: cookieValue,
                    companyId: item.companyId,
                    recruitId: item.recruitId
                }
            })
                .then(response => {
                    let data = response.data
                    let code = data.code
                    let msg = data.msg
                    if (code === 300001) {
                        console.log(data)
                        item.userId = ""
                        alert(msg)
                    } else {
                        console.log(data)
                        alert(msg)
                    }
                })
        },
        /* 关注企业的方法 */
        follow() {
            let that = this;
            let companyId = this.$route.query.companyId;
            let cookieValue = Cookies.get('cookieUserId');
            axios({
                method: "get",
                url: '/api/follow/followCompany/'+companyId+'/'+cookieValue,
            })
                .then(response => {
                    let data = response.data
                    let code = data.code
                    let msg = data.msg
                    if (code === 100001) {
                        console.log(data)
                        alert(msg)
                        that.followStatus = false
                    } else {
                        console.log(data)
                        alert(msg)
                    }
                })
        },
        unfollow() {
            let that = this
            let companyId = this.$route.query.companyId;
            let cookieValue = Cookies.get('cookieUserId');
            axios({
                method: "get",
                url: '/api/follow/unfollowCompany/'+companyId+'/'+cookieValue,
            })
                .then(response => {
                    let data = response.data
                    let code = data.code
                    let msg = data.msg
                    if (code === 100001) {
                        console.log(data)
                        alert(msg)
                        that.followStatus = true
                    } else {
                        console.log(data)
                        alert(msg)
                    }
                })
        }
    },
    mounted() {
        let companyId = this.$route.query.companyId
        let that = this;
        const cookieValue = Cookies.get('cookieUserId');
        /* 获取企业信息 */
        axios({
            method: 'GET',
            url: '/api/company/getCompanyInfo/' + companyId //你的后端路径
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
            /* 获取企业下所有招聘信息 */
            axios({
                method: 'GET',
                url: '/api/company/getCompanyRecruit/' + companyId + '/' + cookieValue //你的后端路径
            })
                .then(response => {
                    let data = response.data
                    let code = data.code
                    let msg = data.msg
                    let info = data.object
                    if (code === 200001) { //判断你的请求是否成功
                        console.log(data)
                        that.arr = info
                    } else {
                        alert(msg)
                    }
                }, error => {
                    console.log('错误', error.message)
                    // alert(error.message)
                }),
                /* 判断企业是否被关注 */
                axios({
                method: 'GET',
                url: '/api/follow/checkFollowStatus/' + companyId + '/' + cookieValue 
            })
                .then(response => {
                    let data = response.data
                    let code = data.code
                    let msg = data.msg
                    let info = data.object
                    if (code === 100001) { 
                        console.log(data)
                        that.followStatus = false
                    } else {
                        console.log(data)
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
    },
}
</script>