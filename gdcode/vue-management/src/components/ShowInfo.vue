<script setup>
import {ref} from 'vue'

let key = ref(false)
let isShowCompany = ref(key.value)
const handleSwitch = () => {
    isShowCompany.value = !isShowCompany.value
}
</script>

<template>
    <el-switch
        @change="handleSwitch"
        id="switch"
        v-model="key"
        size="large"
        active-text="企业信息"
        inactive-text="用户信息"
        style="--el-switch-on-color: #13ce66; --el-switch-off-color: #0582fe;"
    />
    <!-- 内容栏 -->
    <el-row :gutter="10">
        <el-col v-if="!isShowCompany" :span="24">
            <div class="grid-content gray-color">
                <UserInfo />
            </div>
        </el-col>
        <el-col v-if="isShowCompany" :span="24">
            <div class="grid-content gray-color">
                <CompanyInfo />
            </div>
        </el-col>
    </el-row>
</template>