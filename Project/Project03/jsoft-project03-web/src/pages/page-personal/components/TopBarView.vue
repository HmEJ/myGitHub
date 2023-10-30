<template>
    <div>
        <div class="header">
            <div class="headerMain">
                <div class="Notice">
                </div>
                <div class="Account">
                    <div class="Left">
                        <div class="Info">
                            <font>{{ userName }}</font>
                            <input type="button">
                            <div id="InfoPopup">
                                <ul>
                                    <li><a @click="logOut()" href="/index">退出</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="navTop">
            <div class="navTopmain">
                <div class="logo"><a href="/index"><img src="/static/images/logo_1.png" /><img src="/static/images/logo_2.png" /></a>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import Cookies from 'js-cookie';
export default {
    data () {
        return {
            userName:"",//用户名
        }
    },
    mounted() {
        this.userName = Cookies.get('cookieUserName');
        

    },
    methods: {
        logOut() {
            // 获取所有Cookie的名称
            const cookieNames = Object.keys(this.$cookie.get());
            // 清空所有Cookie
            cookieNames.forEach(cookieName => {
                this.$cookie.remove(cookieName);
            });
            location.reload()
        }
    },
}
</script>