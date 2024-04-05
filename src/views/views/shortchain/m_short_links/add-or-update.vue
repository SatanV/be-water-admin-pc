<template>
	<el-dialog v-model="visible" :title="!dataForm.id ? '新增' : '修改'" :close-on-click-modal="false">
		<el-form ref="dataFormRef" :model="dataForm" :rules="dataRules" label-width="100px" @keyup.enter="submitHandle()">
	    				<el-form-item label="" prop="id">
					<el-input v-model="dataForm.id" placeholder=""></el-input>
				</el-form-item>
				<el-form-item label="短链URL" prop="shortUrl">
					<el-input v-model="dataForm.shortUrl" placeholder="短链URL"></el-input>
				</el-form-item>
				<el-form-item label="原始URL" prop="originalUrl">
					<el-input v-model="dataForm.originalUrl" placeholder="原始URL"></el-input>
				</el-form-item>
					<el-form-item label="短链类型(1-课程,2-其他)" prop="shortType">
						<el-select v-model="dataForm.shortType" placeholder="请选择">
							<el-option label="请选择" value="0"></el-option>
						</el-select>
					</el-form-item>
				<el-form-item label="今日访问次数" prop="pageViews">
					<el-input v-model="dataForm.pageViews" placeholder="今日访问次数"></el-input>
				</el-form-item>
				<el-form-item label="今日独立访客数" prop="uniqueVisitors">
					<el-input v-model="dataForm.uniqueVisitors" placeholder="今日独立访客数"></el-input>
				</el-form-item>
				<el-form-item label="今日获客次数" prop="acquiredCustomers">
					<el-input v-model="dataForm.acquiredCustomers" placeholder="今日获客次数"></el-input>
				</el-form-item>
				<el-form-item label="累计访问次数" prop="pvTotal">
					<el-input v-model="dataForm.pvTotal" placeholder="累计访问次数"></el-input>
				</el-form-item>
				<el-form-item label="累计独立访客次数" prop="uvTotal">
					<el-input v-model="dataForm.uvTotal" placeholder="累计独立访客次数"></el-input>
				</el-form-item>
				<el-form-item label="累计获客次数" prop="acTotal">
					<el-input v-model="dataForm.acTotal" placeholder="累计获客次数"></el-input>
				</el-form-item>
				<el-form-item label="状态 (0=禁用, 1=启用)" prop="status">
					<el-input v-model="dataForm.status" placeholder="状态 (0=禁用, 1=启用)"></el-input>
				</el-form-item>
				<el-form-item label="渠道ID" prop="channelId">
					<el-input v-model="dataForm.channelId" placeholder="渠道ID"></el-input>
				</el-form-item>
				<el-form-item label="渠道名称" prop="channelName">
					<el-input v-model="dataForm.channelName" placeholder="渠道名称"></el-input>
				</el-form-item>
				<el-form-item label="网站域名ID" prop="domainId">
					<el-input v-model="dataForm.domainId" placeholder="网站域名ID"></el-input>
				</el-form-item>
				<el-form-item label="网站域名名称" prop="domainName">
					<el-input v-model="dataForm.domainName" placeholder="网站域名名称"></el-input>
				</el-form-item>
				<el-form-item label="创建人ID" prop="creatorId">
					<el-input v-model="dataForm.creatorId" placeholder="创建人ID"></el-input>
				</el-form-item>
				<el-form-item label="创建人名称" prop="creatorName">
					<el-input v-model="dataForm.creatorName" placeholder="创建人名称"></el-input>
				</el-form-item>
				<el-form-item label="创建时间" prop="createTime">
					<el-date-picker type="datetime" placeholder="创建时间" v-model="dataForm.createTime"></el-date-picker>
				</el-form-item>
		</el-form>
		<template #footer>
			<el-button @click="visible = false">取消</el-button>
			<el-button type="primary" @click="submitHandle()">确定</el-button>
		</template>
	</el-dialog>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue'
import { ElMessage } from 'element-plus/es'
import { useM_short_linksApi, useM_short_linksSubmitApi } from '@/api/shortchain/m_short_links'

const emit = defineEmits(['refreshDataList'])

const visible = ref(false)
const dataFormRef = ref()

const dataForm = reactive({
	id: '',
	shortUrl: '',
	originalUrl: '',
	shortType: '',
	pageViews: '',
	uniqueVisitors: '',
	acquiredCustomers: '',
	pvTotal: '',
	uvTotal: '',
	acTotal: '',
	status: '',
	channelId: '',
	channelName: '',
	domainId: '',
	domainName: '',
	creatorId: '',
	creatorName: '',
	createTime: ''})

const init = (id?: number) => {
	visible.value = true
	dataForm.id = ''

	// 重置表单数据
	if (dataFormRef.value) {
		dataFormRef.value.resetFields()
	}

	if (id) {
		getM_short_links(id)
	}
}

const getM_short_links = (id: number) => {
	useM_short_linksApi(id).then(res => {
		Object.assign(dataForm, res.data)
	})
}

const dataRules = ref({
})

// 表单提交
const submitHandle = () => {
	dataFormRef.value.validate((valid: boolean) => {
		if (!valid) {
			return false
		}

		useM_short_linksSubmitApi(dataForm).then(() => {
			ElMessage.success({
				message: '操作成功',
				duration: 500,
				onClose: () => {
					visible.value = false
					emit('refreshDataList')
				}
			})
		})
	})
}

defineExpose({
	init
})
</script>
