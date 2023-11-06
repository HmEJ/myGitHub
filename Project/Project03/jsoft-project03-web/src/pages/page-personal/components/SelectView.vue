<template>
    <div>
        <div class="personalBody">
            <div class="perRightcon">
                <div class="commonTit">
                    <h1 class="fl">职位搜索</h1>
                </div>
                <div class="clear"></div>
                <div class="zwssPart1">
                    <div class="zxssPart1_1">
                        <input name="" v-model="like" type="text" placeholder="请填写关键词或选择职位" />
                    </div>
                    <div class="zxssPart1_4">
                        <input name="" @click="getMyApplyRecruit()" type="button" value="搜工作" />
                    </div>
                </div>
                <div class="clear"></div>
                <div class="zwssPart3">
                    <div class="seachlist">
                        <div class="listtop">
                            <div class="tj1" :class="{ on: !isOn }">
                                <span class="list" :class="{ on: isOn }" @click="showOn('list')">列表</span>
                                <span class="zhaoyao" :class="{ on: !isOn }" @click="showOn('zhaiyao')">摘要</span>
                            </div>
                            <div class="tj3">
                                <span>共{{ this.dataTotal }}条 第{{ this.pageNum }}/{{ this.pageTotal }}页</span>
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="listcon">
                            <table border="0" width="100%" cellpadding="0" cellspacing="0" class="listtab">
                                <thead>
                                    <tr>
                                        <th width="24%" style="padding-left:17px">职位名称</th>
                                        <th width="20%">企业名称</th>
                                        <th width="9%"></th>
                                        <th width="10%">工作地点</th>
                                        <th width="10%">薪水</th>
                                        <th width="15%">刷新时间</th>
                                    </tr>
                                </thead>
                                <tbody v-for="item in arr" :key="item.recruitId">
                                    <tr>
                                        <td>
                                            <a :href="'/info?companyId='+item.companyId+'&recruitId='+item.recruitId" class="jobname">{{ item.recruitName }}</a>
                                        </td>
                                        <td>
                                            <div class="company">
                                                <a :href="'/info/company?companyId=' + item.companyId">{{ item.companyName
                                                }}</a>
                                            </div>
                                        </td>
                                        <td></td>
                                        <td>{{ item.recruitAddress }}</td>
                                        <td><span>{{ item.recruitSalaryMin }}K-{{ item.recruitSalaryMax }}K</span></td>
                                        <td>{{ new Date(item.recruitTime).toLocaleString() }}</td>
                                    </tr>
                                    <tr class="xxdetail" :class="{ none: isNone }">
                                        <td colspan="5">
                                            <div class="yaoqiu">学历要求：{{ item.recruitDegree }} | 工作经验：{{ item.recruitExp }} | 公司规模：{{ item.companyScale }} | 招聘方式：{{ item.recruitType }}<br />
                                                岗位职责：{{ item.recruitTextDuty.slice(0,50)+"..." }}<br />
                                                岗位要求：{{ item.recruitTextNeed.slice(0,50)+"..." }}
                                            </div>
                                        </td>
                                        <td>
                                            <div class="applydiv">
                                                <a href="javascript:void(0);"  class="ysqbtn">已申请</a>
                                                <span :class="{'ygz':item.followStatus == 1}">{{ item.followStatus == 1?"已关注":"未关注" }}</span>
                                            </div>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="clear"></div>
                        <div class="listbottom">
                            <div class="lb3"><span>共{{ this.dataTotal }}条 第{{ this.pageNum }}/{{ this.pageTotal }}页</span>
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="fenyediv">
                            <a href="javascript:void(0)" @click="goToFirstPage()" class="fy1">首页</a>
                            <a href="javascript:void(0)" @click="goToPreviousPage()" class="fy1">上一页</a>
                            <a href="javascript:void(0)" v-for="(page, index) in this.pageTotal" :key="index"
                                :class="{ 'fy2': true, 'on': page == pageNum }" @click="goToPage(page)">
                                {{ page }}
                            </a>
                            <a href="javascript:void(0)" @click="goToNextPage()" class="fy1">下一页</a>
                            <a href="javascript:void(0)" @click="goToLastPage()" class="fy1">尾页</a>
                        </div>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="clear"></div>
        <div class="footer"></div>
    </div>
</template>
<script>
import axios from 'axios'
import Cookies from 'js-cookie';
export default {
    data() {
        return {
            arr: "",
            isOn: true,
            isNone: true,
            userId: "",
            dataTotal: "",//总数据条数
            pageNum: 1,//第几页
            pageTotal: "",//总几页       
            prePage: "",//前一页
            nextPage: "",//后一页
            like:"",
        }
    },
    methods: {
        showOn(status) {
            if (status == 'list') {
                this.isOn = true,
                    this.isNone = true
            } else {
                this.isOn = false,
                    this.isNone = false
            }
        },
        getMyApplyRecruit() {
            let that = this
            /* 获取进入页面的申请职位信息 */
            axios({
                method: 'GET',
                url: '/api/apply/getAllUserApply/' + that.pageNum + '/' + that.userId+'?like='+ that.like
            })
                .then(response => {
                    let data = response.data
                    let code = data.code
                    let msg = data.msg
                    let info = data.object
                    if (code === 200001) { //判断你的请求是否成功
                        console.log(data)
                        this.arr = info.list
                        that.pageTotal = info.pages
                        that.dataTotal = info.total
                        that.prePage = info.prePage
                        that.nextPage = info.nextPage
                    } else {
                        alert(msg)
                    }
                }, error => {
                    console.log('错误', error.message)
                    // alert(error.message)
                })
        },
        // 跳转到第一页
        async goToFirstPage() {
            if (this.pageNum != 1) {
                this.goToPage(1);
            }
        },
        // 跳转到上一页
        async goToPreviousPage() {
            if (this.pageNum > 1) {
                this.goToPage(this.pageNum - 1);
            }
        },
        // 跳转到下一页
        async goToNextPage() {
            if (this.pageNum < this.pageTotal) {
                this.goToPage(this.pageNum + 1);
            }
        },
        // 跳转到最后一页
        async goToLastPage() {
            if (this.pageNum != this.pageTotal) {
                this.goToPage(this.pageTotal);
            }
        },
         goToPage(page) {
            const cookieValue = Cookies.get('cookieUserId');
            this.pageNum = page
            console.log(this.pageNum)
            this.getMyApplyRecruit()
        },
    },
    mounted() {
        const cookieValue = Cookies.get('cookieUserId');
        this.userId = cookieValue
        this.getMyApplyRecruit(null, this.pageNum, cookieValue)
    }
}
</script>