<template>
	<el-card>
		<el-form :inline="true" :model="state.queryForm" @keyup.enter="getDataList()">
					<el-form-item>
				<el-button @click="getDataList()">查询</el-button>
			</el-form-item>
			<el-form-item>
				<el-button v-auth="'shortchain:m_short_links:save'" type="primary" @click="addOrUpdateHandle()">新增</el-button>
			</el-form-item>
			<el-form-item>
				<el-button v-auth="'shortchain:m_short_links:delete'" type="danger" @click="deleteBatchHandle()">删除</el-button>
			</el-form-item>
		</el-form>
		<el-table v-loading="state.dataListLoading" :data="state.dataList" border style="width: 100%" @selection-change="selectionChangeHandle">
			<el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
			<el-table-column prop="shortUrl" label="短链URL" header-align="center" align="center"></el-table-column>
			<el-table-column prop="originalUrl" label="原始URL" header-align="center" align="center"></el-table-column>
			<el-table-column prop="shortType" label="短链类型(1-课程,2-其他)" header-align="center" align="center"></el-table-column>
			<el-table-column prop="pageViews" label="今日访问次数" header-align="center" align="center"></el-table-column>
			<el-table-column prop="uniqueVisitors" label="今日独立访客数" header-align="center" align="center"></el-table-column>
			<el-table-column prop="acquiredCustomers" label="今日获客次数" header-align="center" align="center"></el-table-column>
			<el-table-column prop="pvTotal" label="累计访问次数" header-align="center" align="center"></el-table-column>
			<el-table-column prop="uvTotal" label="累计独立访客次数" header-align="center" align="center"></el-table-column>
			<el-table-column prop="acTotal" label="累计获客次数" header-align="center" align="center"></el-table-column>
			<el-table-column prop="status" label="状态 (0=禁用, 1=启用)" header-align="center" align="center"></el-table-column>
			<el-table-column prop="channelName" label="渠道名称" header-align="center" align="center"></el-table-column>
			<el-table-column prop="domainName" label="网站域名名称" header-align="center" align="center"></el-table-column>
			<el-table-column prop="creatorName" label="创建人名称" header-align="center" align="center"></el-table-column>
			<el-table-column prop="createTime" label="创建时间" header-align="center" align="center"></el-table-column>
			<el-table-column label="操作" fixed="right" header-align="center" align="center" width="150">
				<template #default="scope">
					<el-button v-auth="'shortchain:m_short_links:update'" type="primary" link @click="addOrUpdateHandle(scope.row.id)">修改</el-button>
					<el-button v-auth="'shortchain:m_short_links:delete'" type="primary" link @click="deleteBatchHandle(scope.row.id)">删除</el-button>
				</template>
			</el-table-column>
		</el-table>
		<el-pagination
			:current-page="state.page"
			:page-sizes="state.pageSizes"
			:page-size="state.limit"
			:total="state.total"
			layout="total, sizes, prev, pager, next, jumper"
			@size-change="sizeChangeHandle"
			@current-change="currentChangeHandle"
		>
		</el-pagination>

		<!-- 弹窗, 新增 / 修改 -->
		<add-or-update ref="addOrUpdateRef" @refreshDataList="getDataList"></add-or-update>
	</el-card>
</template>

<script setup lang="ts" name="ShortchainM_short_linksIndex">
	import {useCrud} from '@/hooks'
	import {reactive, ref} from 'vue'
	import {IHooksOptions} from '@/hooks/interface'
	import AddOrUpdate from './add-or-update.vue'

	const state: IHooksOptions = reactive({
	dataListUrl: '/shortchain/m_short_links/page',
	deleteUrl: '/shortchain/m_short_links',
	queryForm: {
	}
})

const addOrUpdateRef = ref()
const addOrUpdateHandle = (id?: number) => {
	addOrUpdateRef.value.init(id)
}

const { getDataList, selectionChangeHandle, sizeChangeHandle, currentChangeHandle, deleteBatchHandle } = useCrud(state)
</script>
