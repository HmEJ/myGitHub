<script setup>
import router from '@/router';

const toHome = function(){
  router.push("/showInfo")
}

const toAddCom = ()=>{
  router.push("/addCom")
}

const toAddUser = ()=>{
  router.push("/addUser")
}

</script>

<template>
    <!-- 菜单栏 -->
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" :ellipsis="false"
        @select="handleSelect">
        <el-menu-item @click="toHome" index="0">
            <img id="logo" src="/public/MYHD.svg" alt="logo" />
        </el-menu-item>
        <div id="flex-grow1" />
        <el-menu-item @click="toHome" index="1">首页</el-menu-item>
        <el-sub-menu index="2">
            <template #title>控制台</template>
            <el-sub-menu index="2-1">
                <template #title>企业控制</template>
                <el-menu-item @click="toAddCom" index="2-1-1">新增企业信息</el-menu-item>
            </el-sub-menu>
            <el-sub-menu index="2-2">
                <template #title>用户控制</template>
                <el-menu-item @click="toAddUser" index="2-2-1">新增用户信息</el-menu-item>
            </el-sub-menu>
        </el-sub-menu>
        <div id="flex-grow2"></div>
    </el-menu>
</template>

<style scoped>
#logo {
  width: 37px;
  height: 37px;
  margin-top: 6px;
}

#switch{
  margin-top: 10px;
}

#flex-grow0{
  flex-grow: 0.5;
}

#flex-grow1 {
  flex-grow: 1;
}

#flex-grow2 {
  flex-grow: 0;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.gray-color {
  background-color: rgb(178, 171, 171);
}
</style>