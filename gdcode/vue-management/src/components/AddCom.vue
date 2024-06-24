<script setup>
import { ref } from 'vue'
import axios from 'axios'
let formData = ref({})

const onSubmit = () => {
    try {
        console.log("表单数据:" + formData.value)
        axios.post('/api/user-info', formData.value)
            .then((res) => {
                if (res.status === 200) {
                    // eslint-disable-next-line no-undef
                    ElMessage({
                        message: "成功!",
                        type: "success"
                    })
                }
            })
        formData.value = ref({})
    } catch (error) {
        console.log("出现异常", error)
    }
}
const resetForm = () => {
    formData.value = ref({})
}
</script>

<template>
    <el-form :model="formData">
        <el-form-item label="企业编号" :label-width="formLabelWidth">
            <el-input disabled v-model="formData.companyId" autocomplete="off" />
        </el-form-item>
        <el-form-item label="企业名称" :label-width="formLabelWidth">
            <el-input v-model="formData.companyName" autocomplete="off" />
        </el-form-item>
        <el-form-item label="企业类型" :label-width="formLabelWidth">
            <el-input v-model="formData.companyType"></el-input>
        </el-form-item>
        <el-form-item label="企业规模" :label-width="formLabelWidth">
            <el-input v-model="formData.companyScale" autocomplete="off" />
        </el-form-item>
        <el-form-item label="企业领域" :label-width="formLabelWidth">
            <el-input v-model="formData.companyField" autocomplete="off" />
        </el-form-item>
        <el-form-item label="企业位置" :label-width="formLabelWidth">
            <el-input v-model="formData.companyAddress" autocomplete="off" />
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit">提交</el-button>
            <el-button @click="resetForm">重置</el-button>
        </el-form-item>
    </el-form>
</template>