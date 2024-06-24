<script setup>
import axios from 'axios';
// import { ElMessage } from 'element-plus';
import { ref } from 'vue'

let tableData = ref([])

axios.get('/api/manage/getCompanyInfo')
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

let formData = ref()
let dialogFormVisible = ref(false)
const handleEdit = (index, row) => {
    console.log(index, row)
    formData.value = { ...row }
    dialogFormVisible.value = true
}
const submitForm = async () => {
    console.log("提交的表单数据:" + JSON.stringify(formData.value))
    try {
        await axios.post('/api/manage/updateCompanyInfo', formData.value)
            .then((res) => {
                if (res.status === 200) {
                    // eslint-disable-next-line no-undef
                    ElMessage({
                        message: "提交成功!",
                        type: "success"
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
        formData.value = {}
        dialogFormVisible.value = false
        window.location.reload()
    }

}

const handleDelete = (index, row) => {
    console.log(index, row)
    let companyId = row.companyId
    try {
        axios.post(`/api/manage/deleteCompanyInfo/${companyId}`)
            .then((res) => {
                if (res === 200) {
                    // eslint-disable-next-line no-undef
                    ElMessage({
                        type: "success",
                        message: "删除成功!"
                    })
                } else {
                    // eslint-disable-next-line no-undef
                    ElMessage.error("服务繁忙!")
                }
            })
    } catch (error) {
        console.log("捕获异常:",error)
        // eslint-disable-next-line no-undef
        ElMessage.error("服务异常!")
    } finally {
        window.location.reload()
    }
}

</script>

<template>
    <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="companyId" label="企业编号" />
        <el-table-column prop="companyName" label="企业名称" />
        <el-table-column prop="companyType" label="企业类型" />
        <el-table-column prop="companyScale" label="企业规模" />
        <el-table-column prop="companyField" label="企业领域" />
        <el-table-column prop="companyAddress" label="企业位置" />
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