<script setup>
import { ref } from 'vue'
import axios from 'axios'
let formData = ref({})

const onSubmit = () => {
    try {
        console.log("表单数据:" + formData.value)
        axios.post('/api/manage/addUser', formData.value)
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
        <el-form-item label="姓名" :label-width="formLabelWidth">
            <el-input v-model="formData.name"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" :label-width="formLabelWidth">
            <el-input v-model="formData.email" autocomplete="off" />
        </el-form-item>
        <el-form-item label="电话" :label-width="formLabelWidth">
            <el-input v-model="formData.phone" autocomplete="off" />
        </el-form-item>
        <el-form-item label="性别" :label-width="formLabelWidth">
            <el-input v-model="formData.gender" autocomplete="off" />
        </el-form-item>
        <el-form-item label="学校" :label-width="formLabelWidth">
            <el-input v-model="formData.schoolName" autocomplete="off" />
        </el-form-item>
        <el-form-item label="学位" :label-width="formLabelWidth">
            <el-input v-model="formData.degree" autocomplete="off" />
        </el-form-item>
        <el-form-item label="专业" :label-width="formLabelWidth">
            <el-input v-model="formData.majorName" autocomplete="off" />
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit">提交</el-button>
            <el-button @click="resetForm">重置</el-button>
        </el-form-item>
    </el-form>
</template>