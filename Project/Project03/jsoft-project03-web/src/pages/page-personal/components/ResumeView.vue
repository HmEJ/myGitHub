<template>
    <div>
        <div class="personalBody">
            <div id="base_div" v-show="show_base" class="perRightcon">
                <div class="JlBoxTit">
                    <p class="tit1">基本资料/求职意向</p>
                    <div class="tit2"></div>
                    <p class="tit3">最高学历</p>
                </div>
                <div class="JlBoxCon">
                    <h1><span>基本信息</span>
                        <div></div>
                    </h1>
                    <div class="JlBoxLeft">
                        <div class="jldiv">
                            <div class="nametxt"><span>*</span>姓 名：</div>
                            <div class="inputtxt">
                                <input name="" type="text" v-model="trueName" class="txt1" :disabled="isDisabled"/>
                            </div>
                            <div class="clear"></div>
                        </div>
                        <div class="jldiv">
                            <div class="nametxt"><span>*</span>性 别：</div>
                            <div class="inputtxt">
                                <label for="man">
                                    <div class="sex" :class="{ on: show_sex }" @click="showSex('man')" v-if="showMan">男</div>
                                </label>
                                <input type="radio" id="man" value="1" v-model="gender" name="man" v-show="false">
                                <label for="woman">
                                    <div class="sex" :class="{ on: !show_sex }" @click="showSex('girl')" v-if="showWoman">女</div>
                                </label>
                                <input type="radio" id="woman" value="0" v-model="gender" name="woman" v-show="false">
                                <div class="clear"></div>
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="jldiv">
                            <div class="nametxt"><span>*</span>出生年月：</div>
                            <div class="inputtxt">
                                <input name="" type="date" class="txt8" v-model="birthday" :disabled="isDisabled"/>
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="jldiv">
                            <div class="nametxt"><span>*</span>现居住地：</div>
                            <div class="inputtxt">
                                <input name="" type="text" class="txt7" v-model="resident" :disabled="isDisabled"/>
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="jldiv">
                            <div class="nametxt"><span>*</span>户口所在地：</div>
                            <div class="inputtxt">
                                <input name="" type="text" class="txt7" v-model="place" :disabled="isDisabled"/>
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="jldiv">
                            <div class="nametxt"><span>*</span>我成长在：</div>
                            <div class="inputtxt">
                                <input name="" type="text" class="txt7" v-model="growPlace" :disabled="isDisabled"/>
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="jldiv">
                            <div class="nametxt"><span>*</span>手机号码：</div>
                            <div class="inputtxt">
                                <input name="" type="text" class="txt3" v-model="phone" @blur="checkPhone"  :readonly="!phoneStatus" :disabled="isDisabled"/>
                                <b :class="{ 'red-text' : isRed}" v-if="phoneStatus">{{ phoneInfo }}</b>
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="jldiv">
                            <div class="nametxt"><span>*</span>电子邮箱：</div>
                            <div class="inputtxt">
                                <input name="" type="text" class="txt3" v-model="email"  @blur="checkEmail"  :readonly="!emailStatus" :disabled="isDisabled"/>
                                <b :class="{ 'red-text' : isRed}" v-if="emailStatus">{{ emailInfo }}</b>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="clear"></div>
                <div class="JlBoxCon">
                    <h1><span>求职意向</span>
                        <div></div>
                    </h1>
                    <div class="JlBoxLeft">
                        <div class="jldiv">
                            <div class="nametxt"><span>*</span>求职状态：</div>
                            <div class="inputtxt">
                                <select class="txtcon txt2" v-model="jobStatus" :disabled="isDisabled">
                                    <option value="0">离职-随时到岗</option>
                                    <option value="1">在职-月内到岗</option>
                                    <option value="2">在职-考虑机会</option>
                                    <option value="3">在职-暂不考虑</option>
                                </select>
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="jldiv">
                            <div class="nametxt"><span>*</span>相关工作经验：</div>
                            <div class="inputtxt">
                                <label for="have">
                                    <div class="typeJob" :class="{ on: isActive }" @click="showJob('yes')" v-if="showHave" >有</div>
                                </label>
                                <input type="radio" id="have" value="1" v-model="workExp" name="man" v-show="false" >

                                <label for="not-have">
                                    <div class="typeJob" :class="{ on: !isActive }" @click="showJob('no')" v-if="showNot">无</div>
                                </label>
                                <input type="radio" id="not-have" value="0" v-model="workExp" name="man" v-show="false">
                                <div class="clear"></div>
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="jldiv">
                            <div class="nametxt"><span>*</span>期望工作性质：</div>
                            <div class="inputtxt">
                                <label for="full">
                                    <div class="typeJob" :class="{ on: show1 }" @click="showThreeJob(1)" v-if="showFull">全职</div>
                                </label>
                                <input type="radio" id="full" value="0" v-model="wantedNature" name="man" v-show="false">

                                <label for="part">
                                    <div class="typeJob" :class="{ on: show2 }" @click="showThreeJob(2)" v-if="showPart">兼职</div>
                                </label>
                                <input type="radio" id="part" value="1" v-model="wantedNature" name="man" v-show="false">

                                <label for="practice">
                                    <div class="typeJob" :class="{ on: show3 }" @click="showThreeJob(3) " v-if="showPractice">实习</div>
                                </label>
                                <input type="radio" id="practice" value="2" v-model="wantedNature" name="man"
                                    v-show="false">
                                <div class="clear"></div>
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="jldiv">
                            <div class="nametxt"><span>*</span>期望月薪：</div>
                            <div class="inputtxt">
                                <select class="txtcon txt2" v-model="wantedSalary" :disabled="isDisabled">
                                    <option value="0">面议</option>
                                    <option value="5">30000以上</option>
                                    <option value="4">20000-30000</option>
                                    <option value="3">10000-20000</option>
                                    <option value="2">5000-10000</option>
                                    <option value="1">5000以下</option>
                                </select>
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="jldiv">
                            <div class="nametxt"><span>*</span>期望工作地点：</div>
                            <div class="inputtxt">
                                <input name="" type="text" class="txt7" v-model="wantedLocation" :disabled="isDisabled"/>
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="jldiv">
                            <div class="nametxt"><span>*</span>期望职位类别：</div>
                            <div class="inputtxt">
                                <input name="" type="text" class="txt7" v-model="wantedType" :disabled="isDisabled"/>
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="jldiv">
                            <button id="next_btn" class="nextBtn" @click="show()">进一步完善</button>
                        </div>
                    </div>
                </div>
            </div>
            <div id="education_div" v-show="show_education" class="perRightcon">
                <div class="JlBoxTit">
                    <p class="tit1">基本资料/求职意向</p>
                    <div class="tit2"></div>
                    <p class="tit3 on">最高学历</p>
                </div>
                <div class="JlBoxCon">
                    <h1><span>最高学历</span>
                        <div></div>
                    </h1>
                    <div class="JlBoxLeft">
                        <div class="jldiv">
                            <div class="nametxt"><span>*</span>学校名称：</div>
                            <div class="inputtxt">
                                <input name="" type="text" class="txt3" v-model="schoolName" :disabled="isDisabled"/>
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="jldiv">
                            <div class="nametxt"><span>*</span>毕业时间：</div>
                            <div class="inputtxt">
                                <input id="graduation_time" name="" type="date" class="txt8" v-model="graduationTime" :disabled="isDisabled"/>
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="jldiv">
                            <div class="nametxt"><span>*</span>学历：</div>
                            <div class="inputtxt">
                                <select class="txtcon txt2" v-model="degree" :disabled="isDisabled">
                                    <option value="0">高中</option>
                                    <option value="1">专科</option>
                                    <option value="2">本科</option>
                                    <option value="3">硕士</option>
                                    <option value="4">博士</option>
                                </select>
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="jldiv">
                            <div class="nametxt"><span>*</span>学历类型：</div>
                            <div class="inputtxt">
                                <select class="txtcon txt2" v-model="degreeType" :disabled="isDisabled">
                                    <option value="0">普通高等教育（统招）</option>
                                    <option value="1">普通专升本（统招）</option>
                                    <option value="2">成人高考</option>
                                    <option value="3">自考</option>
                                    <option value="4">电大</option>
                                    <option value="5">网络教育</option>
                                </select>
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="jldiv">
                            <div class="nametxt"><span>*</span>专业名称：</div>
                            <div class="inputtxt">
                                <input name="" type="text" class="txt3" v-model="majorName" :disabled="isDisabled"/>
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="jldiv">
                            <div class="nametxt"><span>*</span>专业类别：</div>
                            <div class="inputtxt">
                                <select class="txtcon txt2" v-model="majorType" :disabled="isDisabled">
                                    <option value="0">哲学</option>
                                    <option value="1">经济学</option>
                                    <option value="2">法学</option>
                                    <option value="3">教育学</option>
                                    <option value="4">文学</option>
                                    <option value="5">历史学</option>
                                    <option value="6">理学</option>
                                    <option value="7">工学</option>
                                    <option value="8">农学</option>
                                    <option value="9">医学</option>
                                    <option value="10">军事学</option>
                                    <option value="11">管理学</option>
                                    <option value="12">艺术学</option>
                                </select>
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="jldiv">
                            <button class="saveBtn" @click="addUserInfo" v-show="!isDisabled">填写完毕</button>
                            <button id="previous_btn" class="preBtn" @click="show()" style="">上一步</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="clear"></div>
        <div class="footer"></div>
    </div>
</template>
<script>
import axios from 'axios';
import Cookies from 'js-cookie';
export default {
    data() {
        return {
            show_base: true,
            show_education: false,
            show_sex: true,
            isActive: true,
            show1: true,
            show2: false,
            show3: false,
            trueName: "",//姓名
            gender: 1,//性别
            birthday: "",//出生日期
            resident: "",//现居住地
            place: "",//户口所在地
            growPlace: "",//成长所在地
            phone: "",//手机号码
            email: "",//电子邮件
            jobStatus: 0,//求职状态
            workExp: 1,//工作经验
            wantedNature: 0,//期望工作性质
            wantedSalary: 0,//期望月薪
            wantedLocation: "",//期望工作地点
            wantedType: "",//期望职位类别
            schoolName: "",//学校名称
            graduationTime: "",//毕业时间
            degree: 0,//学历
            degreeType: 0,//学历类型
            majorName: "",//专业名称
            majorType: 0,//专业类别
            /* 状态中false为只读状态,不显示提示信息 */
            phoneStatus: false,
            phoneInfo:"可用来登录",
            emailStatus: false,
            emailInfo:"可用来登录",
            isRed: false,
            isDisabled:false,
            showMan:false,
            showWoman:false,
            showHave:false,
            showNot:false,
            showFull:false,
            showPart:false,
            showPractice:false
        }
    },
    methods: {
        show() {
            this.show_base = !this.show_base
            this.show_education = !this.show_education
        },
        showSex(sex) {
            if (sex == 'man') {
                this.show_sex = true
            } else {
                this.show_sex = false
            }
        },
        showJob(status) {
            if (status == 'yes') {
                this.isActive = true
            } else {
                this.isActive = false
            }
        },
        showThreeJob(i) {
            this.show1 = false,
                this.show2 = false,
                this.show3 = false
            if (i == 1) {
                this.show1 = true
            }
            if (i == 2) {
                this.show2 = true
            }
            if (i == 3) {
                this.show3 = true
            }
        },
        /* 数据回显 */
        echoData(){
            let that = this;
            const cookieValue = Cookies.get('cookieUserId');
            axios({
                method: 'GET',
                url: '/api/user-info/getUserInfo/'+cookieValue
            })
                .then(response => {
                    let data = response.data
                    let code = data.code
                    let msg = data.msg
                    let  info = data.object
                    if (code === 200001) {
                        /* 从用户信息表获取到数据的话进行数据回显 */
                        console.log(data)
                        this.trueName =  info.name
                        const backendDate = new Date(info.birthday); // 使用ISO 8601日期字符串创建Date对象
                        backendDate.setDate(backendDate.getDate() + 1);
                        this.birthday = backendDate.toISOString().split('T')[0];
                        this.resident = info.resident
                        this.place =  info.place
                        this.growPlace =  info.growPlace
                        this.phone = info.phone
                        this.email = info.email
                        this.wantedLocation = info.wantedLocation
                        this.wantedType = info.wantedType
                        this.schoolName = info.schoolName
                        this.majorName = info.majorName 
                        const  backendDate2 = new Date(info.graduationTime); // 使用ISO 8601日期字符串创建Date对象
                        backendDate2.setDate(backendDate2.getDate() + 1);
                        this.graduationTime = backendDate2.toISOString().split('T')[0];
                        this.isDisabled = true
                        /* 多选框显示 */
                        if(info.gender == 0){
                            this.showMan = false
                            this.showWoman = true
                            this.show_sex = false
                        }else{
                            this.showWoman = false
                            this.showMan = true
                            this.show_sex = true
                        }
                        if(info.workExp == 0){
                            this.showNot = true
                            this.showHave = false
                            this.isActive = false
                        }else{
                            this.showNot = false
                            this.showHave = true
                            this.isActive = true
                        }

                        this.show1 = false,
                        this.show2 = false,
                        this.show3 = false
                        if (info.wantedNature == 0) {
                            this.show1 = true
                            this.showFull = true
                            this.showPart = false
                            this.showPractice = false
                        }
                        if (info.wantedNature == 1) {
                            this.show2 = true
                            this.showFull = false
                            this.showPart = true
                            this.showPractice = false
                        }
                        if (info.wantedNature == 2) {
                            this.show3 = true
                            this.showFull = false
                            this.showPart = false
                            this.showPractice = true
                        }
                    } else {
                        console.log(msg)
                        this.showNot = true
                        this.showHave = true
                        this.showMan = true
                        this.showWoman = true
                        this.showFull = true
                        this.showPart = true
                        this.showPractice = true
                        that.getPhoneOrEmail();
                    }
                }, error => {
                    console.log('错误', error.message)
                })
        },
        getPhoneOrEmail() {
            let that = this;
            let cookieValue = Cookies.get('cookieUserId');
            axios({
                method: 'GET',
                url: '/api/user-info/getPhoneOrEmail/'+cookieValue //你的后端路径
            })
                .then(response => {
                    let data = response.data
                    let code = data.code
                    let msg = data.msg
                    if (code === 200001) { //判断你的请求是否成功
                        console.log(data)
                        if (data.object.phone == "") {
                            that.email = data.object.email
                            that.phoneStatus = true
                        } else {
                            that.phone = data.object.phone
                            that.emailStatus = true
                        }
                    } else {
                        alert(msg)
                    }
                }, error => {
                    console.log('错误', error.message)
                    // alert(error.message)
                })
        },
        addUserInfo() {
            let that = this;
            let cookieValue = Cookies.get('cookieUserId');
            if (
                this.trueName != "" 
                && this.birthday != ""
                && this.resident != ""
                && this.place !=  ""
                && this.growPlace !=  ""
                && this.phone != ""
                && this.email != ""
                && this.wantedLocation !=  ""
                && this.wantedType != ""
                && this.schoolName != ""
                && this.majorName != "" 
                ) {
                    /* 如果信息填写完整则进行请求发送 */
                    axios({
                    method: 'post',
                    url: '/api/user-info',
                    data: {
                        userId: cookieValue,
                        name: that.trueName,
                        gender: that.gender,
                        birthday: that.birthday,
                        resident: that.resident,
                        place: that.place,
                        growPlace: that.growPlace,
                        phone: that.phone,
                        email: that.email,
                        jobStatus: that.jobStatus,
                        workExp: that.workExp,
                        wantedNature: that.wantedNature,
                        wantedSalary: that.wantedSalary,
                        wantedLocation: that.wantedLocation,
                        wantedType: that.wantedType,
                        schoolName: that.schoolName,
                        graduationTime: that.graduationTime,
                        degree: that.degree,
                        degreeType: that.degreeType,
                        majorName: that.majorName,
                        majorType: that.majorType,
                    }
                })
                .then(response => {
                    let data = response.data
                    let code = data.code
                    let msg = data.msg
                    if (code === 300001) {
                        /* 进行数据回显 */
                        console.log(data.object)
                        alert(msg)
                       this.echoData()
                        this.show_base = !this.show_base
                        this.show_education = !this.show_education
                    } else {
                        alert(msg)
                    }
                }, error => {
                    console.log('错误', error.message)
                    // alert(error.message)
                })
            }else{
                alert("请填写完整信息")
            }
        },
        /* 表单校验 */
        checkPhone(){
            let phone_REG = /^(0\d{2,3}-\d{7,8}(-\d{1,6})?|[1-9]\d{6,7}(-\d{1,6})?|1[3-9]\d{9})$/;
            if (phone_REG.test(this.phone)) {
                this.isRed = false;
                this.phoneInfo = "可用于登录"
            }else{
                this.isRed = true;
                this.phoneInfo = "手机格式不正确"
            }
        },
        checkEmail(){
            let email_REG = /^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.(com|cn)$/;
            if (email_REG.test(this.email)) {
                this.isRed = false;
                this.emailInfo = "可用于登录"
            }else{
                this.isRed = true;
                this.emailInfo = "邮箱格式不正确"
            }
        }
    },
    mounted() {
        this.echoData();

    }
}
</script>