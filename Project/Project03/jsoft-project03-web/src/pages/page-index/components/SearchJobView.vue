<template>
    <div>
        <div class="topDiv">
            <div class="topL">
                <div class="logo">
                    <a href="/index">
                        <img src="/static/images/LOGO.png" />
                        <img src="/static/images/view_logo40.png" class="img1" />
                    </a>
                </div>
                <img src="/static/images/spirit_40.png" />
            </div>
            <div class="topR">
                <div class="topR0 topR2"><a @click="logOut()" href="#">退出登陆</a></div>
                <div class="topR0 topR2"><a href="/login/register">注册用户</a></div>
                <div class="topR0 topR3 loginHrefClass"><a name="loginHref" :href=loginhref>{{ userName }}</a></div>

                <div class="clear"></div>
            </div>
        </div>
        <div class="menuDiv">
            <div class="mainWarp">
            </div>
        </div>
        <div class="clear"></div>
        <div class="menuLittle">
        </div>
        <div class="clear"></div>
        <div class="searchDiv">
            <div class="search1">
                <div class="sea1_1">
                    <input name="" v-model="word" type="text" placeholder="请填写关键词或选择职位" class="keyword" />
                    <!-- <span class="jiantou"></span> -->
                </div>
                <div class="sea1_4">
                    <a @click="query()" href="javascript:void(0)">搜工作</a>
                </div>
            </div>
        </div>
        <div class="clear"></div>
        <div class="seachlist">
            <div class="listtop">
                <div class="tj1" :class="{ on: !isOn }">
                    <span class="list" :class="{ on: isOn }" @click="showOn('list')">列表</span>
                    <span class="zhaoyao" :class="{ on: !isOn }" @click="showOn('zhaiyao')">摘要</span>
                </div>
                <div class="tj3">
                    <span>共{{ total }}条 第{{ pageNum }}/{{ pages }}页</span>
                </div>
            </div>
            <div class="clear"></div>
            <div class="listcon">
                <table border="0" width="100%" cellpadding="0" cellspacing="0" class="listtab">
                    <thead>
                        <tr>
                            <th width="20%" style="padding-left:17px">职位名称</th>
                            <th width="25%" style="padding-left:17px">企业名称</th>
                            <th width="8%" style="padding-left:17px"></th>
                            <th width="20%" style="padding-left:17px">工作地点</th>
                            <th width="11%" style="padding-left:17px">薪水</th>
                            <th width="15%" style="padding-left:17px">刷新时间</th>
                        </tr>
                    </thead>
                    <tbody v-for="(item, index) in recruitList" :key="index">
                        <tr>
                            <td>
                                <a :href="'/info?companyId='+item.companyId+'&recruitId='+item.recruitId" class="jobname">{{ item.recruitName }}</a>
                            </td>
                            <td>
                                <div class="company">
                                    <a :href="'/info/company?companyId='+item.companyId" >{{ item.companyName }}</a>
                                </div>
                            </td>
                            <td></td>
                            <td>{{ item.recruitAddress }}</td>
                            <td><span>{{ item.recruitSalaryMin }}k - {{ item.recruitSalaryMax }}k</span></td>
                            <td>{{ new Date(item.recruitTime).toLocaleString() }}</td>
                        </tr>
                        <tr class="xxdetail" :class="{ none: isNone }">
                            <td colspan="5">
                                <div class="yaoqiu">学历要求：{{ item.recruitDegree }} | 工作经验：{{ item.recruitExp }} | 公司规模：{{
                                    item.companyScale }} | 招聘方式：{{ item.recruitType }}<br />
                                    岗位职责：{{ item.recruitTextDuty }}<br />
                                    岗位要求：{{ item.recruitTextNeed }}</div>
                            </td>
                            <td>
                                <div class="applydiv">
                                    <a :href="'/info?companyId='+item.companyId+'&recruitId='+item.recruitId" @click="apply(item)" :class="{'ljsqbtn':item.userId == null,'ysqbtn':item.userId != null}">{{ item.userId == null ? '立即申请' : '已申请' }}</a>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="clear"></div>
            <div class="listbottom">
                <div class="lb3"><span>共{{ total }}条 第{{ pageNum }}/{{ pages }}页</span></div>
            </div>
            <div class="clear"></div>
            <div class="fenyediv">
                <a href="javascript:void(0)" @click="goToFirstPage()" class="fy1">首页</a>
                <a href="javascript:void(0)" @click="goToPreviousPage()" class="fy1">上一页</a>
                <!-- <a href="javascript:void(0)" class="fy2 on">1</a> -->
                <a href="javascript:void(0)" v-for="(page, index) in this.pages" :key="index"
                    :class="{ 'fy2': true, 'on': page == pageNum }" @click="goToPage(page)">
                    {{ page }}
                </a>

                <a href="javascript:void(0)" @click="goToNextPage()" class="fy1">下一页</a>
                <a href="javascript:void(0)" @click="goToLastPage()" class="fy1">尾页</a>
            </div>
            <div class="clear"></div>
        </div>
        <div class="clear"></div>
        <div class="footer">
        </div>
    </div>
</template>
<script>
// edit by JoneElmo 10-29
import axios from "axios"
export default {
    name: 'indexTopBar',
    data() {
        return {
            arr: new Array(10),
            isOn: true,
            isNone: true,

            userName: null,

            loginhref: "#",

            word: "",

            result: null,

            recruitList: [],

            //总数据条数
            total: null,
            //总页数
            pages: null,
            //前一页
            prePage: null,
            //后一页
            nextPage: null,
            //当前页
            pageNum: 1,
            //页面数据量
            pageSize: null,

            //快捷查询编号
            jobFiled: null,
        }
    },
    methods: {
        async loadDataForPage() {
            let that = this
            // 获取数据
            axios({
                method: 'get',
                url: '/api/recruit/queryRecruitList?word=' + that.word + "&pageNum=" + that.pageNum
            })
                .then(function (result) {
                    that.result = result
                    that.setPageInfo()
                })
        },
        async loadDataForPage2() {
            let that = this
            // 获取数据
            axios({
                method: 'get',
                url: '/api/recruit/quickQueryRecruitList?jobFiled=' + that.jobFiled + "&pageNum=" + that.pageNum
            })
                .then(function (result) {
                    that.result = result
                    console.log(result.data.object)
                    console.log(result.data.object.list)
                    that.recruitList = result.data.object.list
                    //注入分页信息
                    that.total = result.data.object.total
                    that.pages = result.data.object.pages
                    that.prePage = result.data.object.prePage
                    that.nextPage = result.data.object.nextPage
                    that.pageSize = result.data.object.pageSize
                })
        },
        query() {
            // 模糊查询发送请求
            let that = this
            //获取全部数据
            axios({
                method: 'get',
                url: '/api/recruit/queryRecruitList?word=' + that.word + "&pageNum=" + that.pageNum
            })
                .then(function (result) {
                    that.result = result
                    that.setPageInfo()
                })
        },
        // 跳转到第一页
        async goToFirstPage() {
            this.goToPage(1);
        },
        // 跳转到上一页
        async goToPreviousPage() {
            if (this.pageNum > 1) {
                this.goToPage(this.pageNum - 1);
            }
        },
        // 跳转到下一页
        async goToNextPage() {
            if (this.pageNum < this.pages) {
                this.goToPage(this.pageNum + 1);
            }
        },
        // 跳转到最后一页
        async goToLastPage() {
            this.goToPage(this.pages);
        },
        async goToPage(page) {
            this.pageNum = page
            console.log("切换的页码是" + this.pageNum)
            console.log("总页码数量是" + this.pages)
            console.log("切换页码工作领域的值为" + this.jobFiled)
            if (this.jobFiled == null) {
                console.log("走loadDataForPage")
                await this.loadDataForPage(this.pageNum)
            } else {
                console.log("走loadDataForPage2")
                await this.loadDataForPage2(this.pageNum)
            }
        },
        setPageInfo() {
            console.log("接收到的数据信息：")
            console.log(this.result.data.object)
            console.log("接收到的列表信息：")
            console.log(this.result.data.object.list)
            this.recruitList = this.result.data.object.list
            //注入分页信息
            this.total = this.result.data.object.total
            this.pages = this.result.data.object.pages
            this.prePage = this.result.data.object.prePage
            this.nextPage = this.result.data.object.nextPage
            this.pageSize = this.result.data.object.pageSize
        },
        logOut() {
            // 获取所有Cookie的名称
            const cookieNames = Object.keys(this.$cookie.get());
            // 清空所有Cookie
            cookieNames.forEach(cookieName => {
                this.$cookie.remove(cookieName);
            });
            location.reload()
        },
        showOn(status) {
            if (status == 'list') {
                this.isOn = true,
                    this.isNone = true
            } else {
                this.isOn = false,
                    this.isNone = false
            }
        },
        quickSearch() {
            let that = this
            axios({
                method: 'get',
                url: '/api/recruit/quickQueryRecruitList?jobFiled=' + that.jobFiled + "&pageNum=" + that.pageNum,
            })
                .then(function (result) {
                    console.log(result.data.object)
                    that.result = result
                    that.setPageInfo()
                })
        }
    },
    mounted() {
        this.word = this.$route.query.word
        this.jobFiled = this.$route.query.jobFiled
        console.log("接收到的关键字:" + this.word)
        console.log("接收到的快捷查询编号:" + this.jobFiled)
        const userName = this.$cookie.get("cookieUserName")
        // console.log(userName+";"+userId+";"+userEmail+";"+userPhone)
        console.log(this.$cookie.get())
        if (userName != null && userName != "") {
            console.log("用户已登陆")
            this.userName = userName
            this.loginhref = "/personal/resume"
        } else {
            this.userName = "个人登陆"
            this.loginhref = "/login"
        }

        if (this.jobFiled != null) {
            this.quickSearch()
        } else {
            this.query()
        }

    }
}
</script>
<style>
.loginHrefClass {
    margin-right: 50px;
}
</style>