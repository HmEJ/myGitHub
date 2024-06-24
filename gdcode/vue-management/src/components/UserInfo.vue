<script setup>
import axios from 'axios';
import { ref } from 'vue'

let tableData = ref([])

axios.get('/api/manage/getAllUserInfo')
    .then((res) => {
        if (res.status === 200) {
            console.log(res.data.object)
            tableData.value = JSON.parse(res.data.object)
        } else {
            // eslint-disable-next-line no-undef
            ElMessage.error("服务繁忙!")
        }

    })
    .catch(() => {
        // eslint-disable-next-line no-undef
        ElMessage.error("服务器异常!")
    })

let dialogFormVisible = ref(false)
let formData = ref([])

const handleEdit = (index, row) => {
    formData.value = { ...row }
    dialogFormVisible.value = true
}
//提交弹出框表单数据
const submitForm = async () => {
    // 在这里提交表单数据
    console.log('提交的表单数据：', JSON.stringify(formData.value))
    try {
        //提交请求
        await axios.post('/api/manage/updateUserInfo', formData.value)
            .then((res) => {
                if (res.status === 200) {
                    // eslint-disable-next-line no-undef
                    ElMessage({
                        message: '提交成功!',
                        type: 'success',
                    })
                } else {
                    // eslint-disable-next-line no-undef
                    ElMessage.error("服务繁忙!")
                }
            })
    } catch (error) {
        console.error('请求出错:', error);
        // 显示错误提示消息
        // eslint-disable-next-line no-undef
        ElMessage.error("服务器异常!请重试!");
    } finally {
        // 清空formData，准备下次使用
        formData.value = {}
        // 关闭对话框
        dialogFormVisible.value = false
        // 重新加载页面
        window.location.reload();
    }

}
//删除数据
const handleDelete = (index, row) => {
    let userId = row.userId
    console.log(userId)
    axios.post(`/api/manage/deleteUserInfo/${userId}`)
        .then((res) => {
            if (res.status === 200) {
                // eslint-disable-next-line no-undef
                ElMessage({
                    message: '删除成功!',
                    type: 'success',
                })
            } else {
                // eslint-disable-next-line no-undef
                ElMessage.error("服务器繁忙!")
            }
        })
}

</script>

<template>
    <!-- 数据表格 -->
    <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="userId" label="编号"> </el-table-column>
        <el-table-column prop="userName" label="用户名" />
        <el-table-column prop="email" label="邮箱" />
        <el-table-column prop="phone" label="电话" />
        <el-table-column prop="name" label="姓名" />
        <el-table-column prop="gender" label="性别" />
        <el-table-column prop="schoolName" label="学校" />
        <el-table-column prop="degree" label="学位" />
        <el-table-column prop="majorName" label="专业" />
        <el-table-column align="right">
            <template #default="scope">
                <el-button size="small" @click="handleEdit(scope.$index, scope.row)">更新</el-button>
                <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
        </el-table-column>
    </el-table>
    <!-- 对话框 -->
    <el-dialog v-model="dialogFormVisible" title="提交信息" width="500">
        <el-form :model="formData">
            <el-form-item label="编号" :label-width="formLabelWidth">
                <el-input disabled v-model="formData.userId" autocomplete="off" />
            </el-form-item>
            <el-form-item label="用户名" :label-width="formLabelWidth">
                <el-input v-model="formData.userName" autocomplete="off" />
            </el-form-item>
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
        </el-form>
        <template #footer>
            <div class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取消</el-button>
                <el-button type="primary" @click=submitForm()>
                    确认
                </el-button>
            </div>
        </template>
    </el-dialog>
</template>

<style></style>